package com.warmcity.citygrants.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import com.warmcity.citygrants.dto.*;
import com.warmcity.citygrants.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warmcity.citygrants.gridFSDAO.GridFsDAOimpl;
import com.warmcity.citygrants.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {
  
  @Autowired
  private GridFsDAOimpl gridFsDAOimpl;

  @Autowired
  private ProjectRepository projectRepository;

  @Autowired
  private UploadingService uploadingService;
  
  @Override
  public Project getProjectById(String id) {

    return projectRepository.findOne(id);
  }

  @Override
  public List<Project> getAllProjects() {
    List<Project> projects = projectRepository.findAll();
    projects.forEach(project -> project.setFilesInfo(uploadingService.getAllFilesInfoForProject(project.getId())));

    return projects;
  }

  @Override
  public List<ProjectApplJuryDTO> getAllJuryProjects(String juryId){
    List<Project> listProjects = getAllProjects();
    List<ProjectApplJuryDTO>listProjectJury = new ArrayList<>();
    listProjects.forEach(project -> { listProjectJury.add(getProjectsForJury(project,juryId));});

    return listProjectJury;
  }

  private ProjectApplJuryDTO getProjectsForJury(Project project, String juryId){
    ProjectApplJuryDTO projectDTO = new ProjectApplJuryDTO();
    projectDTO.setId(project.getId());
    projectDTO.setDescription(project.getDescription());
    projectDTO.setBudget(project.getBudget());
    projectDTO.setComments(project.getComments());
    projectDTO.setConfirmed(project.isConfirmed());
    projectDTO.setEvaluation(getEvalutionForJury(project.getEvaluations(),juryId));
    projectDTO.setInterviewEvaluation(getInterviewEvalutionForJury(project.getInterviewEvaluations(),juryId));
    projectDTO.setFilesInfo(project.getFilesInfo());
    projectDTO.setApprovedToSecondStage(project.isApprovedToSecondStage());

    return projectDTO;
  }

  private Evaluation getEvalutionForJury(List<Evaluation> evaluations, String juryId){
    return evaluations.stream().filter(eval -> eval.getJuryMemberId().equals(juryId)).findFirst().orElseGet(()->getDefaultEvalution(juryId));
  }

  private InterviewEvaluation getInterviewEvalutionForJury(List<InterviewEvaluation> evaluations, String juryId){
    return evaluations.stream().filter(eval -> eval.getJuryMemberId().equals(juryId)).findFirst().orElseGet(()->getDefaultInterviewEvalution(juryId));
  }

  private Evaluation getDefaultEvalution(String juryId){

    return  new Evaluation(juryId,"",0,0,0,0,0,0,0,0);
  }
  private InterviewEvaluation getDefaultInterviewEvalution(String juryId){

    return  new InterviewEvaluation(juryId,"",0);
  }

  @Override
  public String saveProject(ProjectApplicationDTO projectDTO) {

    return projectRepository.insert(projectBuilder(projectDTO)).getId();
  }

  @Override
  public void updateProject(Project project) {

    projectRepository.save(project);
  }

  @Override

  /*
  It is temporary method, it need refactoring, probably here need will write query for updating evaluation
   */
  public void updateEvaluation(String idProject, Evaluation evaluation) {
      Project project = getProjectById(idProject);

      int equalId = 0;
      for (int index = 0; index < project.getEvaluations().size(); index++){
        if(project.getEvaluations().get(index).getJuryMemberId().equals(evaluation.getJuryMemberId())){
          ++equalId;
          project.getEvaluations().set(index, evaluation);
          break;
        }
      }
      if(equalId == 0){
        project.getEvaluations().add(evaluation);
      }

      updateProject(project);
  }

  @Override
  public void updateInterviewEvaluation(String idProject, InterviewEvaluation evaluation){
    Project project = getProjectById(idProject);

    int equalId = 0;
    for (int index = 0; index < project.getInterviewEvaluations().size(); index++){
      if(project.getInterviewEvaluations().get(index).getJuryMemberId().equals(evaluation.getJuryMemberId())){
        ++equalId;
        project.getInterviewEvaluations().set(index, evaluation);
        break;
      }
    }
    if(equalId == 0){
      project.getInterviewEvaluations().add(evaluation);
    }

    updateProject(project);
  }

  @Override
  public void saveComment(String idProject, Comment comment){
    Project project = getProjectById(idProject);
    List<Comment> comments = project.getComments();
    comments.add(comment);
    project.setComments(comments);

    updateProject(project);
  }
  
  @Override
  public void deleteProject(String id) {
    
    projectRepository.delete(id);
    gridFsDAOimpl.deleteAllByProjectId(id);
  }

  @Override
  public void deleteCommentOfProject(String idProject, String idComment){
    Project project = getProjectById(idProject);
    List<Comment> comments = project.getComments();
    comments.stream()
            .filter(comment -> comment.getId().equals(idComment))
            .findFirst()
            .ifPresent(comment -> {comments.remove(comment);});

    updateProject(project);
  }

  private Project projectBuilder(ProjectApplicationDTO projectDTO) {

    Project project = new Project();
    project.setConfirmed(projectDTO.isConfirmed());
    project.setDescription(descriptionBuilder(projectDTO.getDescription()));
    project.setBudget(budgetBuilder(projectDTO.getBudget()));
    project.setEvaluations(new ArrayList<Evaluation>());
    project.setInterviewEvaluations(new ArrayList<InterviewEvaluation>());
    project.setComments(new ArrayList<Comment>());
    return project;
  }

  private Description descriptionBuilder(DescriptionDTO descriptionDTO) {

    Description description = new Description();
    description.setName(descriptionDTO.getName());
    description.setRequestedBudget(Integer.parseInt(descriptionDTO.getRequestedBudget()));
    description.setOrganizationName(descriptionDTO.getOrganizationName());
    description.setTheme(descriptionDTO.getTheme());
    description.setRequiredTime(descriptionDTO.getRequiredTime());
    description.setCoordinatorName(descriptionDTO.getCoordinatorName());
    description.setCoordinatorEmail(descriptionDTO.getCoordinatorEmail());
    description.setCoordinatorPhone(descriptionDTO.getCoordinatorPhone());
    description.setProjectMembers(descriptionDTO.getProjectMembers());
    description.setExpirienceDescription(descriptionDTO.getExpirienceDescription());
    description.setAddress(descriptionDTO.getAddress());
    description.setWebaddress(descriptionDTO.getWebaddress());
    description.setGoal(descriptionDTO.getGoal());
    description.setActuality(descriptionDTO.getActuality());
    description.setFullDescription(descriptionDTO.getFullDescription());
    description.setTargetGroup(descriptionDTO.getTargetGroup());
    description.setExpectedResults(descriptionDTO.getExpectedResults());
    description.setRequiredPermissions(descriptionDTO.getRequiredPermissions());
    description.setPartners(descriptionDTO.getPartners());
    description.setDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
    return description;
  }

  private Budget budgetBuilder(BudgetDTO budgetDTO) {

    Budget budget = new Budget();

    budget.setCostItemsFee(costItemListBuilder(budgetDTO.getCostItemsFee()));
    budget.setTotalFeeFromProgram(getSumFromProgram(budget.getCostItemsFee()));
    budget.setTotalFeeFromOtherSources(getSumFromOtherSources(budget.getCostItemsFee()));

    budget.setCostItemsNutrition(costItemListBuilder(budgetDTO.getCostItemsNutrition()));
    budget.setTotalNutritionFromProgram(getSumFromProgram(budget.getCostItemsNutrition()));
    budget.setTotalNutritionFromOtherSources(getSumFromOtherSources(budget.getCostItemsNutrition()));

    budget.setCostItemsTransport(costItemListBuilder(budgetDTO.getCostItemsTransport()));
    budget.setTotalTransportFromProgram(getSumFromProgram(budget.getCostItemsTransport()));
    budget.setTotalTransportFromOtherSources(getSumFromOtherSources(budget.getCostItemsTransport()));

    budget.setCostItemsRent(costItemListBuilder(budgetDTO.getCostItemsRent()));
    budget.setTotalRentFromProgram(getSumFromProgram(budget.getCostItemsRent()));
    budget.setTotalRentFromOtherSources(getSumFromOtherSources(budget.getCostItemsRent()));

    budget.setCostItemsAdministrative(costItemListBuilder(budgetDTO.getCostItemsAdministrative()));
    budget.setTotalAdministrativeFromProgram(getSumFromProgram(budget.getCostItemsAdministrative()));
    budget.setTotalAdministrativeFromOtherSources(getSumFromOtherSources(budget.getCostItemsAdministrative()));

    budget.setCostItemsAdvertising(costItemListBuilder(budgetDTO.getCostItemsAdvertising()));
    budget.setTotalAdvertisingFromProgram(getSumFromProgram(budget.getCostItemsAdvertising()));
    budget.setTotalAdvertisingFromOtherSources(getSumFromOtherSources(budget.getCostItemsAdvertising()));

    budget.setCostItemsMaterial(costItemListBuilder(budgetDTO.getCostItemsMaterial()));
    budget.setTotalMaterialsFromProgram(getSumFromProgram(budget.getCostItemsMaterial()));
    budget.setTotalMaterialsFromOtherSources(getSumFromOtherSources(budget.getCostItemsMaterial()));

    budget.setCostItemsOthers(costItemListBuilder(budgetDTO.getCostItemsOthers()));
    budget.setTotalOthersFromProgram(getSumFromProgram(budget.getCostItemsOthers()));
    budget.setTotalOthersFromOtherSources(getSumFromOtherSources(budget.getCostItemsOthers()));

    budget.setTotalFromProgram(getTotalFromProgram(budget));
    budget.setTotalFromOtherSources(getTotalFromOtherSources(budget));

    return budget;
  }

  private int getSumFromOtherSources(List<CostItem> costItems) {

    return costItems.stream().mapToInt(CostItem::getConsumptionsFromOtherSources).sum();

  }

  private int getSumFromProgram(List<CostItem> costItems) {

    return costItems.stream().mapToInt(CostItem::getConsumptionsFromProgram).sum();

  }

  private int getTotalFromOtherSources(Budget budget) {

    return budget.getTotalAdministrativeFromOtherSources() + budget.getTotalAdvertisingFromOtherSources()
        + budget.getTotalFeeFromOtherSources() + budget.getTotalMaterialsFromOtherSources()
        + budget.getTotalNutritionFromOtherSources() + budget.getTotalRentFromOtherSources()
        + budget.getTotalTransportFromOtherSources() + budget.getTotalOthersFromOtherSources();
  }

  private int getTotalFromProgram(Budget budget) {

    return budget.getTotalAdministrativeFromProgram() + budget.getTotalAdvertisingFromProgram()
        + budget.getTotalFeeFromProgram() + budget.getTotalMaterialsFromProgram()
        + budget.getTotalNutritionFromProgram() + budget.getTotalRentFromProgram()
        + budget.getTotalTransportFromProgram() + budget.getTotalOthersFromProgram();
  }

  private List<CostItem> costItemListBuilder(List<CostItemDTO> costItemDTOs) {

    return costItemDTOs.stream().map(costItemDTO -> costItemBuilder(costItemDTO)).collect(Collectors.toList());
  }

  private CostItem costItemBuilder(CostItemDTO costItemDTO) {

    CostItem costItem = new CostItem();
    costItem.setDescription(costItemDTO.getDescription());
    costItem.setCost(costItemDTO.getCost());
    costItem.setCount(costItemDTO.getCount());
    costItem.setConsumptionsFromOtherSources(Integer.parseInt(costItemDTO.getConsumptionsFromOtherSources()));
    costItem.setConsumptionsFromProgram(Integer.parseInt(costItemDTO.getConsumptionsFromProgram()));
    return costItem;

  }

}
