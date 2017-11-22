package com.warmcity.citygrants.services;

import java.util.List;

import com.warmcity.citygrants.dto.ProjectApplicationDTO;
import com.warmcity.citygrants.models.Project;

public interface ProjectService {

  String save(ProjectApplicationDTO projectDTO);

  List<Project> getAll();

}
