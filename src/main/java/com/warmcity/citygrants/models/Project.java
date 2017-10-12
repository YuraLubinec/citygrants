package com.warmcity.citygrants.models;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="projects")
public class Project implements Serializable {

  private static final long serialVersionUID = 5071347014610298630L;
  
  @Id
  private String id;
  private Description description;
  private Budget budget;
  private List <Evaluation> evaluations;
  private List <InterviewEvaluation> interviewEvaluations;
  private List <Comment> comments;
  private boolean confirmed;
  private boolean approvedToSecondStage;
  private int totalEvalFirstStage;
  private int totalEvalSecondStage;
  
}
