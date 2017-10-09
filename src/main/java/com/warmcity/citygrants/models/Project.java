package com.warmcity.citygrants.models;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class Project implements Serializable {

  private static final long serialVersionUID = 5071347014610298630L;
  
  private String id;
  private Description projectDescription;
  private Budget projectBudget;
  private List <Evaluation> evaluations;
  private List <InterviewEvaluation> interviewEvaluations;
  private List <Comment> comments;
  private boolean confirmed;
  private boolean approvedToSecondStage;
  private int totalEvalFirstStage;
  private int totalEvalSecondStage;
  
}
