package com.warmcity.citygrants.controllers;

import com.warmcity.citygrants.dto.AttachmentsDTO;
import com.warmcity.citygrants.dto.ProjectApplicationDTO;
import com.warmcity.citygrants.services.ProjectService;
import com.warmcity.citygrants.services.UploadingService;
import com.warmcity.citygrants.validators.AttachmentsValidator;
import com.warmcity.citygrants.validators.UniqueProjectNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.AbstractMap;
import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientPageController {

  @Autowired
  private ProjectService projectService;

  @Autowired
  private UploadingService uploadingService;

  @Autowired
  private UniqueProjectNameValidator nameValidator;

  @Autowired
  private AttachmentsValidator attachmentsValidator;

  @InitBinder("projectApplicationDTO")
  public void initBinderName(WebDataBinder binder) {
    binder.addValidators(nameValidator);
  }

  @InitBinder("attachmentsDTO")
  public void initBinderAttachments(WebDataBinder binder) {
    binder.addValidators(attachmentsValidator);
  }

  @PostMapping("/project")
  public ResponseEntity<Map.Entry<String, String>> saveProject(
      @Validated @RequestBody ProjectApplicationDTO projectApplicationDTO) {

    Map.Entry<String, String> entry = new AbstractMap.SimpleEntry<String, String>("id",
        projectService.saveProject(projectApplicationDTO));
    return new ResponseEntity<Map.Entry<String, String>>(entry, HttpStatus.OK);

  }

  @GetMapping("/project/isUniqName/{projectName}")
  public boolean isUniqNameProject(@PathVariable String projectName){
    return projectService.isUniqueNameProject(projectName);
  }

  @PostMapping(path = "/project/file")
  public void uploadFiles(@Validated @ModelAttribute AttachmentsDTO attachmentsDTO) {

    uploadingService.uploadFilesToDb(attachmentsDTO);
  }

}
