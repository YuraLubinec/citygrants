package com.warmcity.citygrants.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.warmcity.citygrants.models.Project;
import com.warmcity.citygrants.repositories.ProjectRepository;

@Component
public class ProjectUpdateValidator implements Validator {

  @Autowired
  private ProjectRepository repository;

  @Override
  public boolean supports(Class<?> clazz) {

    return Project.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    Project project = (Project) target;
    Project dbProject = repository.findOneByDescriptionName(project.getDescription().getName());
    if (dbProject != null && project.getId() != dbProject.getId()) {
      errors.rejectValue("description.name", "project.name.not.unique", "Name not unique");
    }
  }

}
