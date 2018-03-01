package com.warmcity.citygrants.dto;

import java.io.Serializable;
import java.util.List;

import com.warmcity.citygrants.models.Budget;
import com.warmcity.citygrants.models.Comment;
import com.warmcity.citygrants.models.Description;
import com.warmcity.citygrants.models.Evaluation;
import com.warmcity.citygrants.models.FileInfo;
import com.warmcity.citygrants.models.InterviewEvaluation;

import lombok.Data;

@Data
public class ProjectApplJuryDTO implements Serializable {

  private static final long serialVersionUID = -7213683740640852924L;
  private String id;
  private Description description;
  private Budget budget;
  private Evaluation evaluation;
  private InterviewEvaluation interviewEvaluation;
  private List<Comment> comments;
  private boolean confirmed;
  private boolean approvedToSecondStage;
  private List<FileInfo> filesInfo;
}
