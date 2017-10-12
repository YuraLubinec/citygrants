package com.warmcity.citygrants.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warmcity.citygrants.models.Project;
import com.warmcity.citygrants.repositories.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService {

  @Autowired
  private ProjectRepository projectRepository;
  
  @Override
  public List<Project> getAll(){

    return projectRepository.findAll();
  }
  
  @Override
  public void save(Project project) {
    
    projectRepository.save(project);
    
  }
  
}
