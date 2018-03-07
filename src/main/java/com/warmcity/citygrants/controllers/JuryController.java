package com.warmcity.citygrants.controllers;

import com.mongodb.gridfs.GridFSDBFile;
import com.warmcity.citygrants.dto.ProjectApplJuryDTO;
import com.warmcity.citygrants.gridFSDAO.GridFsDAO;
import com.warmcity.citygrants.models.Comment;
import com.warmcity.citygrants.models.Evaluation;
import com.warmcity.citygrants.models.InterviewEvaluation;
import com.warmcity.citygrants.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jury")
public class JuryController {

  @Autowired
  private ProjectService projectService;
  @Autowired
  private GridFsDAO gridFsService;

  @GetMapping("/project/{juryLogin}")
  @ResponseStatus(HttpStatus.OK)
  public List<ProjectApplJuryDTO> getAllProjects(@PathVariable String juryLogin) {
    return projectService.getAllJuryProjects(juryLogin);
  }

  @PostMapping("/project/{projectId}/evaluation")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void updateEvaluationOfProject(@PathVariable String projectId, @RequestBody Evaluation evaluation) {
    projectService.updateEvaluation(projectId, evaluation);
  }

    @PutMapping("/project/{projectId}/interviewEvaluation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInterviewEvaluationOfProject(@PathVariable String projectId, @RequestBody InterviewEvaluation evaluation) {
        projectService.updateInterviewEvaluation(projectId, evaluation);
    }

    @PostMapping("/project/{projectId}/comment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void saveComment(@PathVariable String projectId, @RequestBody Comment comment) {
        projectService.saveComment(projectId, comment);
    }

  @GetMapping("/project/file/{fileId}")
  public ResponseEntity<InputStreamResource> getOneFileById(@PathVariable String fileId) {
    GridFSDBFile imageFile = gridFsService.findOneById(fileId);

    return ResponseEntity.ok().contentType(MediaType.valueOf(imageFile.getContentType())).header("Content-Disposition", "inline; filename="+ imageFile.getFilename())
        .body(new InputStreamResource(imageFile.getInputStream()));
  }
}
