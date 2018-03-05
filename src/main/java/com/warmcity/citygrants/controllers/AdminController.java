package com.warmcity.citygrants.controllers;

import java.util.List;

import com.mongodb.gridfs.GridFSDBFile;
import com.warmcity.citygrants.gridFSDAO.GridFsDAO;
import com.warmcity.citygrants.models.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.warmcity.citygrants.dto.UserDTO;
import com.warmcity.citygrants.models.FileInfo;
import com.warmcity.citygrants.models.Project;
import com.warmcity.citygrants.models.User;
import com.warmcity.citygrants.services.ProjectService;
import com.warmcity.citygrants.services.UploadingService;
import com.warmcity.citygrants.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

  @Autowired
  private UploadingService uploadingService;

  @Autowired
  private ProjectService projectService;

  @Autowired
  private UserService userService;

  @Autowired
  private GridFsDAO gridFsService;

  @GetMapping("/user/{login}")
  public UserDTO getUser(@PathVariable String login) {

    return userService.getUserByLogin(login);
  }

  @GetMapping("/user")
  public List<UserDTO> getAllUsers() {

    return userService.getAllUsers();
  }

  @PostMapping("/user")
  public void addUser(@RequestBody @Validated User user) {

    userService.createUser(user);
  }

  @PutMapping("/user")
  public void updateUser(@RequestBody @Validated User user) {

    userService.saveUser(user);
  }

  @DeleteMapping("/user/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable String id) {

    userService.deleteUser(id);
  }

  @GetMapping("/project/{id}")
  public Project getProject(@PathVariable String id) {

    return projectService.getProjectById(id);
  }

  @GetMapping("/project/{id}/fileinfo")
  public List<FileInfo> getAttachmentsInfo(@PathVariable String id) {

    return uploadingService.getAllFilesInfoForProject(id);
  }

  @GetMapping("/project/file/{id}")
  public ResponseEntity<Resource> getFile(@PathVariable String id) {

    Resource file = uploadingService.getFileById(id);
    if (file != null) {
      MultiValueMap<String, String> headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"");
      return new ResponseEntity<Resource>(file, headers, HttpStatus.OK);
    }
    return new ResponseEntity<Resource>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/project")
  public List<Project> getAllProjects() {

    return projectService.getAllProjects();
  }

  @PutMapping("/project")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateProject(@RequestBody Project project) {
    projectService.updateProject(project);
  }

  @DeleteMapping("/project/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteProject(@PathVariable String id) {

    projectService.deleteProject(id);

  }

  @PostMapping("/project/{projectId}/comment")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void saveComment(@PathVariable String projectId, @RequestBody Comment comment) {
    projectService.saveComment(projectId, comment);
  }

  @DeleteMapping("/project/{projectId}/comment/{commentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteComment(@PathVariable String projectId, @PathVariable String commentId) {
    projectService.deleteCommentOfProject(projectId, commentId);
  }

  @GetMapping("/project/files/{fileId}")
  public ResponseEntity<InputStreamResource> getOneFileById(@PathVariable String fileId){
    GridFSDBFile imageFile = gridFsService.findOneById(fileId);

    return ResponseEntity.ok()
            .contentType(MediaType.valueOf(imageFile.getContentType()))
            .body(new InputStreamResource(imageFile.getInputStream()));
  }

}
