package com.warmcity.citygrants.services;

import com.warmcity.citygrants.dto.ProjectApplJuryDTO;
import com.warmcity.citygrants.dto.ProjectApplicationDTO;
import com.warmcity.citygrants.models.Comment;
import com.warmcity.citygrants.models.Evaluation;
import com.warmcity.citygrants.models.InterviewEvaluation;
import com.warmcity.citygrants.models.Project;

import java.util.List;

public interface ProjectService {

  String saveProject(ProjectApplicationDTO projectDTO);

  List<Project> getAllProjects();

  List<ProjectApplJuryDTO> getAllJuryProjects(String juryId);

  Project getProjectById(String id);

  void updateProject(Project project);

  void deleteProject(String id);

  void deleteCommentOfProject(String idProject, String idComment);

  void updateEvaluation(String idProject, Evaluation evaluation);

  void updateInterviewEvaluation(String idProject, InterviewEvaluation evaluation);

  void updateApprovedToSecondStage(String idProject, Boolean isApproved);

  void saveComment(String idProject, Comment comment);

}
