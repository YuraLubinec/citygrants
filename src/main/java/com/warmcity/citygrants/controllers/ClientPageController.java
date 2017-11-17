package com.warmcity.citygrants.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warmcity.citygrants.dto.ProjectApplicationDTO;
import com.warmcity.citygrants.models.Project;
import com.warmcity.citygrants.services.ProjectService;
import com.warmcity.citygrants.validators.UniqueProjectNameValidator;

@RestController
@RequestMapping("/client")
public class ClientPageController {

  @Autowired
  private ProjectService projectService;

  @Autowired
  private UniqueProjectNameValidator nameValidator;

  @InitBinder("projectApplicationDTO")
  public void initBinder(WebDataBinder binder) {
    binder.addValidators(nameValidator);
  }

  @GetMapping("/project")
  public List<Project> getAllProjects() {

    return projectService.getAll();
  }

  @PutMapping(path = "/project", consumes="multipart/form-data")
  public void saveProject(@Validated @RequestBody ProjectApplicationDTO projectApplicationDTO,
      BindingResult bindingResult) {

    if (bindingResult.hasFieldErrors()) {

      for (FieldError error : bindingResult.getFieldErrors()) {
        System.out.println("ERROR***********");
        System.out.println(error.getField() + error.getCode() + error.getDefaultMessage());
      }
    } else {

      projectService.save(projectApplicationDTO);
    }
  }

}
