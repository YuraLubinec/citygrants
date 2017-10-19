package com.warmcity.citygrants.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warmcity.citygrants.dto.BudgetDTO;
import com.warmcity.citygrants.dto.CostItemAdministrativeDTO;
import com.warmcity.citygrants.dto.CostItemAdvertisingDTO;
import com.warmcity.citygrants.dto.CostItemFeeDTO;
import com.warmcity.citygrants.dto.CostItemMaterialDTO;
import com.warmcity.citygrants.dto.CostItemNutritionDTO;
import com.warmcity.citygrants.dto.CostItemRentDTO;
import com.warmcity.citygrants.dto.CostItemTransportDTO;
import com.warmcity.citygrants.dto.DescriptionDTO;
import com.warmcity.citygrants.dto.ProjectApplicationDTO;
import com.warmcity.citygrants.dto.ProjectMemberDTO;
import com.warmcity.citygrants.models.Budget;
import com.warmcity.citygrants.models.Comment;
import com.warmcity.citygrants.models.CostItemAdministrative;
import com.warmcity.citygrants.models.CostItemAdvertising;
import com.warmcity.citygrants.models.CostItemFee;
import com.warmcity.citygrants.models.CostItemMaterial;
import com.warmcity.citygrants.models.CostItemNutrition;
import com.warmcity.citygrants.models.CostItemRent;
import com.warmcity.citygrants.models.CostItemTransport;
import com.warmcity.citygrants.models.Description;
import com.warmcity.citygrants.models.Evaluation;
import com.warmcity.citygrants.models.InterviewEvaluation;
import com.warmcity.citygrants.models.Project;
import com.warmcity.citygrants.models.ProjectMember;
import com.warmcity.citygrants.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  private ProjectRepository projectRepository;

  @Override
  public List<Project> getAll() {

    return projectRepository.findAll();
  }

  @Override
  public void save(ProjectApplicationDTO projectDTO) {

    projectRepository.save(projectBuilder(projectDTO));
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
    description.setProjectMembers(projecMemberListBuilder(descriptionDTO.getProjectMembers()));
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
    budget.setCostItemsFee(costItemFeeListBuilder(budgetDTO.getCostItemFee()));
    budget.setTotalFeeFromProgram(
        budget.getCostItemsFee().stream().mapToInt(CostItemFee::getConsumptionsFromProgram).sum());
    budget.setTotalFeeFromOtherSources(
        budget.getCostItemsFee().stream().mapToInt(CostItemFee::getConsumptionsFromOtherSources).sum());

    budget.setCostItemsNutrition(costItemNutritionListBuilder(budgetDTO.getCostItemNutrition()));
    budget.setTotalNutritionFromProgram(
        budget.getCostItemsNutrition().stream().mapToInt(CostItemNutrition::getConsumptionsFromProgram).sum());
    budget.setTotalNutritionFromOtherSources(
        budget.getCostItemsNutrition().stream().mapToInt(CostItemNutrition::getConsumptionsFromOtherSources).sum());

    budget.setCostItemsTransport(costItemTransportListBuilder(budgetDTO.getCostItemTransport()));
    budget.setTotalTransportFromProgram(
        budget.getCostItemsTransport().stream().mapToInt(CostItemTransport::getConsumptionsFromProgram).sum());
    budget.setTotalTransportFromOtherSources(
        budget.getCostItemsTransport().stream().mapToInt(CostItemTransport::getConsumptionsFromOtherSources).sum());

    budget.setCostItemsRent(costItemRentListBuilder(budgetDTO.getCostItemRent()));
    budget.setTotalRentFromProgram(
        budget.getCostItemsRent().stream().mapToInt(CostItemRent::getConsumptionsFromProgram).sum());
    budget.setTotalRentFromOtherSources(
        budget.getCostItemsRent().stream().mapToInt(CostItemRent::getConsumptionsFromOtherSources).sum());

    budget.setCostItemsAdministrative(costItemAdministrativeListBuilder(budgetDTO.getCostItemAdministrative()));
    budget.setTotalAdministrativeFromProgram(budget.getCostItemsAdministrative().stream()
        .mapToInt(CostItemAdministrative::getConsumptionsFromProgram).sum());
    budget.setTotalAdministrativeFromOtherSources(budget.getCostItemsAdministrative().stream()
        .mapToInt(CostItemAdministrative::getConsumptionsFromOtherSources).sum());

    budget.setCostItemsAdvertising(costItemAdvertisingListBuilder(budgetDTO.getCostItemAdvertising()));
    budget.setTotalAdvertisingFromProgram(
        budget.getCostItemsAdvertising().stream().mapToInt(CostItemAdvertising::getConsumptionsFromProgram).sum());
    budget.setTotalAdvertisingFromOtherSources(
        budget.getCostItemsAdvertising().stream().mapToInt(CostItemAdvertising::getConsumptionsFromOtherSources).sum());

    budget.setCostItemsMaterial(costItemMaterialListBuilder(budgetDTO.getCostItemMaterial()));
    budget.setTotalMaterialsFromProgram(
        budget.getCostItemsMaterial().stream().mapToInt(CostItemMaterial::getConsumptionsFromProgram).sum());
    budget.setTotalMaterialsFromOtherSources(
        budget.getCostItemsMaterial().stream().mapToInt(CostItemMaterial::getConsumptionsFromOtherSources).sum());

    budget.setTotalFromProgram(getTotalFromProgram(budget));
    budget.setTotalFromOtherSources(getTotalFromOtherSources(budget));
    
    return budget;
  }
  
  private int getTotalFromOtherSources(Budget budget) {
    
    return budget.getTotalAdministrativeFromOtherSources() + budget.getTotalAdvertisingFromOtherSources()
    + budget.getTotalFeeFromOtherSources() + budget.getTotalMaterialsFromOtherSources()
    + budget.getTotalNutritionFromOtherSources() + budget.getTotalRentFromOtherSources()
    + budget.getTotalTransportFromOtherSources();
  }

  private int getTotalFromProgram(Budget budget) {
    
    return budget.getTotalAdministrativeFromProgram() + budget.getTotalAdvertisingFromProgram()
        + budget.getTotalFeeFromProgram() + budget.getTotalMaterialsFromProgram()
        + budget.getTotalNutritionFromProgram() + budget.getTotalRentFromProgram()
        + budget.getTotalTransportFromProgram();
  }

  // project member
  private List<ProjectMember> projecMemberListBuilder(List<ProjectMemberDTO> projectMemberDTOs) {

    List<ProjectMember> projectMembers = projectMemberDTOs.stream()
        .map(projectMemberDTO -> projectMemberBuilder(projectMemberDTO)).collect(Collectors.toList());
    return projectMembers;
  }

  private ProjectMember projectMemberBuilder(ProjectMemberDTO projectMemberDTO) {

    ProjectMember projectMember = new ProjectMember();
    projectMember.setEmail(projectMemberDTO.getEmail());
    projectMember.setFullName(projectMemberDTO.getFullName());
    projectMember.setPhone(projectMemberDTO.getPhone());
    return projectMember;
  }

  // fee
  private List<CostItemFee> costItemFeeListBuilder(List<CostItemFeeDTO> feeDTOs) {

    return feeDTOs.stream().map(feeDTO -> costItemFeeBuilder(feeDTO)).collect(Collectors.toList());
  }

  private CostItemFee costItemFeeBuilder(CostItemFeeDTO costItemFeeDTO) {

    CostItemFee costItemFee = new CostItemFee();
    costItemFee.setTitle(costItemFeeDTO.getTitle());
    costItemFee.setMonthAward(Integer.parseInt(costItemFeeDTO.getMonthAward()));
    costItemFee.setTimeSpent(Integer.parseInt(costItemFeeDTO.getTimeSpent()));
    costItemFee.setConsumptionsFromOtherSources(Integer.parseInt(costItemFeeDTO.getConsumptionsFromOtherSources()));
    costItemFee.setConsumptionsFromProgram(Integer.parseInt(costItemFeeDTO.getConsumptionsFromProgram()));
    return costItemFee;
  }

  // nutrition
  private List<CostItemNutrition> costItemNutritionListBuilder(List<CostItemNutritionDTO> nutritionDTOs) {

    return nutritionDTOs.stream().map(nutritionDTO -> costItemNutritionBuilder(nutritionDTO))
        .collect(Collectors.toList());
  }

  private CostItemNutrition costItemNutritionBuilder(CostItemNutritionDTO costItemNutritionDTO) {

    CostItemNutrition costItemNutrition = new CostItemNutrition();
    costItemNutrition.setDescription(costItemNutritionDTO.getDescription());
    costItemNutrition.setCostForPerson(Integer.parseInt(costItemNutritionDTO.getCostForPerson()));
    costItemNutrition.setNumberOfPersons(Integer.parseInt(costItemNutritionDTO.getNumberOfPersons()));
    costItemNutrition
        .setConsumptionsFromOtherSources(Integer.parseInt(costItemNutritionDTO.getConsumptionsFromOtherSources()));
    costItemNutrition.setConsumptionsFromProgram(Integer.parseInt(costItemNutritionDTO.getConsumptionsFromProgram()));
    return costItemNutrition;
  }

  // transport
  private List<CostItemTransport> costItemTransportListBuilder(List<CostItemTransportDTO> transportDTOs) {

    return transportDTOs.stream().map(transportDTO -> costItemTransportBuilder(transportDTO))
        .collect(Collectors.toList());
  }

  private CostItemTransport costItemTransportBuilder(CostItemTransportDTO costItemTransportDTO) {

    CostItemTransport costItemTransport = new CostItemTransport();
    costItemTransport.setDescription(costItemTransportDTO.getDescription());
    costItemTransport.setCostForPerson(Integer.parseInt(costItemTransportDTO.getCostForPerson()));
    costItemTransport.setNumberOfPersons(Integer.parseInt(costItemTransportDTO.getNumberOfPersons()));
    costItemTransport
        .setConsumptionsFromOtherSources(Integer.parseInt(costItemTransportDTO.getConsumptionsFromOtherSources()));
    costItemTransport.setConsumptionsFromProgram(Integer.parseInt(costItemTransportDTO.getConsumptionsFromProgram()));
    return costItemTransport;

  }

  // rent
  private List<CostItemRent> costItemRentListBuilder(List<CostItemRentDTO> rentDTOs) {

    return rentDTOs.stream().map(rentDTO -> costItemRentBuilder(rentDTO)).collect(Collectors.toList());
  }

  private CostItemRent costItemRentBuilder(CostItemRentDTO costItemRentDTO) {

    CostItemRent costItemRent = new CostItemRent();
    costItemRent.setDescription(costItemRentDTO.getDescription());
    costItemRent.setCostForDay(Integer.parseInt(costItemRentDTO.getCostForDay()));
    costItemRent.setTimeSpent(Integer.parseInt(costItemRentDTO.getTimeSpent()));
    costItemRent.setConsumptionsFromOtherSources(Integer.parseInt(costItemRentDTO.getConsumptionsFromOtherSources()));
    costItemRent.setConsumptionsFromProgram(Integer.parseInt(costItemRentDTO.getConsumptionsFromProgram()));
    return costItemRent;

  }

  // administrative
  private List<CostItemAdministrative> costItemAdministrativeListBuilder(
      List<CostItemAdministrativeDTO> administrativeDTOs) {

    return administrativeDTOs.stream().map(administrativeDTO -> costItemAdministrativeBuilder(administrativeDTO))
        .collect(Collectors.toList());
  }

  private CostItemAdministrative costItemAdministrativeBuilder(CostItemAdministrativeDTO costItemAdministrativeDTO) {

    CostItemAdministrative costItemAdministrative = new CostItemAdministrative();
    costItemAdministrative.setDescription(costItemAdministrativeDTO.getDescription());
    costItemAdministrative.setCost(Integer.parseInt(costItemAdministrativeDTO.getCost()));
    costItemAdministrative.setCount(Integer.parseInt(costItemAdministrativeDTO.getCount()));
    costItemAdministrative
        .setConsumptionsFromOtherSources(Integer.parseInt(costItemAdministrativeDTO.getConsumptionsFromOtherSources()));
    costItemAdministrative
        .setConsumptionsFromProgram(Integer.parseInt(costItemAdministrativeDTO.getConsumptionsFromProgram()));
    return costItemAdministrative;
  }

  // advertising
  private List<CostItemAdvertising> costItemAdvertisingListBuilder(List<CostItemAdvertisingDTO> advertisingDTOs) {

    return advertisingDTOs.stream().map(advertisingDTO -> costItemAdvertisingBuilder(advertisingDTO))
        .collect(Collectors.toList());
  }

  private CostItemAdvertising costItemAdvertisingBuilder(CostItemAdvertisingDTO costItemAdvertisingDTO) {

    CostItemAdvertising costItemAdvertising = new CostItemAdvertising();
    costItemAdvertising.setDescription(costItemAdvertisingDTO.getDescription());
    costItemAdvertising.setCost(Integer.parseInt(costItemAdvertisingDTO.getCost()));
    costItemAdvertising.setCount(Integer.parseInt(costItemAdvertisingDTO.getCount()));
    costItemAdvertising
        .setConsumptionsFromOtherSources(Integer.parseInt(costItemAdvertisingDTO.getConsumptionsFromOtherSources()));
    costItemAdvertising
        .setConsumptionsFromProgram(Integer.parseInt(costItemAdvertisingDTO.getConsumptionsFromProgram()));
    return costItemAdvertising;
  }

  // materials
  private List<CostItemMaterial> costItemMaterialListBuilder(List<CostItemMaterialDTO> materialDTOs) {

    return materialDTOs.stream().map(materialDTO -> costItemMaterialBuilder(materialDTO)).collect(Collectors.toList());
  }

  private CostItemMaterial costItemMaterialBuilder(CostItemMaterialDTO costItemMaterialDTO) {

    CostItemMaterial costItemMaterial = new CostItemMaterial();
    costItemMaterial.setDescription(costItemMaterialDTO.getDescription());
    costItemMaterial.setCost(Integer.parseInt(costItemMaterialDTO.getCost()));
    costItemMaterial.setCount(Integer.parseInt(costItemMaterialDTO.getCount()));
    costItemMaterial
        .setConsumptionsFromOtherSources(Integer.parseInt(costItemMaterialDTO.getConsumptionsFromOtherSources()));
    costItemMaterial.setConsumptionsFromProgram(Integer.parseInt(costItemMaterialDTO.getConsumptionsFromProgram()));
    return costItemMaterial;
  }

}
