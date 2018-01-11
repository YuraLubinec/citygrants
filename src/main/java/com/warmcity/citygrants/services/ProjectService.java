package com.warmcity.citygrants.services;

import java.util.List;

import com.warmcity.citygrants.dto.ProjectApplJuryDTO;
import com.warmcity.citygrants.dto.ProjectApplicationDTO;
import com.warmcity.citygrants.models.Comment;
import com.warmcity.citygrants.models.Evaluation;
import com.warmcity.citygrants.models.Project;

public interface ProjectService {

  String saveProject(ProjectApplicationDTO projectDTO);

  List<Project> getAllProjects();

  List<ProjectApplJuryDTO> getAllJuryProjects(String juryId);

  Project getProjectById(String id);

  void updateProject(Project project);

  void deleteProject(String id);

  void updateEvaluation(String idProject, Evaluation evaluation);


  void saveComment(String idProject, Comment comment);


}
