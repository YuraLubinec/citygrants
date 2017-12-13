package com.warmcity.citygrants.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.warmcity.citygrants.models.FileInfo;
import com.warmcity.citygrants.models.Project;
import com.warmcity.citygrants.models.User;
import com.warmcity.citygrants.services.UploadingService;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private UploadingService uploadingService;

  @GetMapping("/user/{id}")
  public User getUser(@PathVariable String id) {

    return new User();
  }

  @GetMapping("/user")
  public List<User> getAllUsers() {

    return new ArrayList<User>();
  }

  @PostMapping("/user")
  public void addUser(@RequestBody User user) {

  }

  @PutMapping("/user")
  public void updateUser(@RequestBody User user) {

  }

  @DeleteMapping("/user")
  public void deleteUser(@RequestBody String id) {

  }

  @GetMapping("/project/{id}")
  public Project getProject(@PathVariable String id) {

    return new Project();
  }

  @GetMapping("/project/{id}/fileinfo")
  public List<FileInfo> getAttachmentsInfo(@PathVariable String id) {

    return uploadingService.getAllFilesInfoForProject(id);
  }

  @GetMapping("/project/file/{id}")
  public ResponseEntity<Resource> getFile(@PathVariable String id) {

    Resource file = uploadingService.getFileById(id);
    MultiValueMap<String, String> headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"");
    return new ResponseEntity<Resource>(file, headers, HttpStatus.OK);
  }

  @GetMapping("/project")
  public List<Project> getAllProjects() {

    return new ArrayList<Project>();
  }

  @PostMapping("/project")
  public void addProject(@RequestBody User user) {

  }

  @PutMapping("/project")
  public void updateProject(@RequestBody User user) {

  }

  @DeleteMapping("/project")
  public void deleteProject(@RequestBody String id) {

  }

}
