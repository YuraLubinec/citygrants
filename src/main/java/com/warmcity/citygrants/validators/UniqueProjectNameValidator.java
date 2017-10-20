package com.warmcity.citygrants.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.warmcity.citygrants.dto.ProjectApplicationDTO;
import com.warmcity.citygrants.repositories.ProjectRepository;

@Component
public class UniqueProjectNameValidator implements Validator {
  
  @Autowired
  private ProjectRepository repository;

  @Override
  public boolean supports(Class<?> clazz) {
    
    return ProjectApplicationDTO.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {
    
    ProjectApplicationDTO dto = (ProjectApplicationDTO) target;
    if (repository.findOneByDescriptionName(dto.getDescription().getName()) != null ) {
      errors.rejectValue("description.name", null , "not unique name");
    };

  }

}
