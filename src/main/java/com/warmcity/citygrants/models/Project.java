package com.warmcity.citygrants.models;

import com.warmcity.citygrants.services.UploadingService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection="projects")
public class Project implements Serializable {
  @Autowired
  private static final long serialVersionUID = 5071347014610298630L;
  private UploadingService uploadingService;

  @Id
  private String id;
  private Description description;
  private Budget budget;
  private List <Evaluation> evaluations;
  private List <InterviewEvaluation> interviewEvaluations;
  private List <Comment> comments;
  private List<FileInfo> filesInfo;
  private boolean confirmed;
  private boolean approvedToSecondStage;
  private int totalEvalFirstStage;
  private int totalEvalSecondStage;

  public List <Evaluation> getEvaluation(){
    return evaluations == null ? new ArrayList<Evaluation>(): this.evaluations;
  }

  public List <Comment> getComments(){
    return this.comments == null ? new ArrayList<Comment>(): this.comments;
  }

  public int getTotalEvalFirstStage() {
    this.totalEvalFirstStage = 0;
    getEvaluations().forEach(evaluation -> {
      this.totalEvalFirstStage += evaluation.getEvalActual();
      this.totalEvalFirstStage += evaluation.getEvalAttracting();
      this.totalEvalFirstStage += evaluation.getEvalCompetence();
      this.totalEvalFirstStage += evaluation.getEvalEfficiency();
      this.totalEvalFirstStage += evaluation.getEvalInnovation();
      this.totalEvalFirstStage += evaluation.getEvalIntelligibility();
      this.totalEvalFirstStage += evaluation.getEvalStability();
      this.totalEvalFirstStage += evaluation.getEvalParticipation();
    });

    return this.totalEvalFirstStage;
  }
}
