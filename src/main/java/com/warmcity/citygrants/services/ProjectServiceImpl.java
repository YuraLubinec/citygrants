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
import com.warmcity.citygrants.dto.CostItemDTO;
import com.warmcity.citygrants.dto.DescriptionDTO;
import com.warmcity.citygrants.dto.ProjectApplicationDTO;
import com.warmcity.citygrants.models.Budget;
import com.warmcity.citygrants.models.Comment;
import com.warmcity.citygrants.models.CostItem;
import com.warmcity.citygrants.models.Description;
import com.warmcity.citygrants.models.Evaluation;
import com.warmcity.citygrants.models.InterviewEvaluation;
import com.warmcity.citygrants.models.Project;
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
  public String save(ProjectApplicationDTO projectDTO) {

    return projectRepository.save(projectBuilder(projectDTO)).getId();

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
