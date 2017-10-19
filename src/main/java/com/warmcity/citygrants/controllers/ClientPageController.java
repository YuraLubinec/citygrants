package com.warmcity.citygrants.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warmcity.citygrants.dto.ProjectApplicationDTO;
import com.warmcity.citygrants.models.Project;
import com.warmcity.citygrants.services.ProjectService;

@RestController
@RequestMapping("/client")
public class ClientPageController {

  @Autowired
  private ProjectService projectService;

  @GetMapping("/project")
  public List<Project> getAllProjects() {

    return projectService.getAll();
  }

  @PutMapping("/project")
  public void saveProject(@Validated @RequestBody ProjectApplicationDTO applicationDTO) {

    projectService.save(applicationDTO);
  }

  /*
   * private Project createDummyProject() { // description List<ProjectMember>
   * projectMembers = new ArrayList<>(); projectMembers.add(new ProjectMember());
   * 
   * Description description = new Description(); description.setName("name");
   * description.setRequestedBudget(10000);
   * description.setOrganizationName("OrganizationName");
   * description.setTheme("theme"); description.setRequiredTime("requiredTime");
   * description.setProjectMembers(projectMembers);
   * description.setExpirienceDescription("expirienceDescription");
   * description.setAddress("address"); description.setWebaddress("webaddress");
   * description.setGoal("goal"); description.setActuality("actuality");
   * description.setFullDescription("fullDescription");
   * description.setTargetGroup("targetGroup");
   * description.setExpectedResults("expectedResults");
   * description.setRequiredPermissions("requiredPermissions");
   * description.setPartners("partners"); description.setDate(new Date());
   * 
   * // cost items for budget CostItemFee costItemFee = new CostItemFee();
   * CostItemTransport costItemTransport = new CostItemTransport();
   * CostItemAdministrative costItemAdministrative = new CostItemAdministrative();
   * CostItemNutrition costItemNutrition = new CostItemNutrition(); CostItemRent
   * costItemRent = new CostItemRent(); CostItemAdvertising costItemAdvertising =
   * new CostItemAdvertising();
   * 
   * // budget Budget budget = new Budget(); budget.setTotalFromProgram(70000);
   * budget.setTotalFromOtherSources(50000); budget.setCostItemFee(costItemFee);
   * budget.setCostItemTransport(costItemTransport);
   * budget.setCostItemNutrition(costItemNutrition);
   * budget.setCostItemRent(costItemRent);
   * budget.setCostItemAdministrative(costItemAdministrative);
   * budget.setCostItemAdvertising(costItemAdvertising);
   * 
   * // project
   * 
   * Evaluation evaluation = new Evaluation(); InterviewEvaluation
   * interviewEvaluation = new InterviewEvaluation(); Comment comment = new
   * Comment();
   * 
   * List<Evaluation> evaluations = new ArrayList<>(); List<InterviewEvaluation>
   * interviewEvaluations = new ArrayList<>(); List<Comment> comments = new
   * ArrayList<>();
   * 
   * evaluations.add(evaluation); interviewEvaluations.add(interviewEvaluation);
   * comments.add(comment);
   * 
   * Project project = new Project(); project.setDescription(description);
   * project.setBudget(budget); project.setConfirmed(true);
   * project.setEvaluations(evaluations);
   * project.setInterviewEvaluations(interviewEvaluations);
   * project.setComments(comments); project.setApprovedToSecondStage(false);
   * project.setTotalEvalFirstStage(33); project.setTotalEvalSecondStage(55);
   * return project; }
   */
}
