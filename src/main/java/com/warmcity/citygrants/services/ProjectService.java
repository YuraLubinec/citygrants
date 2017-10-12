package com.warmcity.citygrants.services;

import java.util.List;

import com.warmcity.citygrants.models.Project;

public interface ProjectService {

  void save(Project project);

  List<Project> getAll();

}
