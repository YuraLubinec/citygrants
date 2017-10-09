package com.warmcity.citygrants.models;

import java.io.Serializable;

import lombok.Data;

@Data 
public class InterviewEvaluation implements Serializable {

  private static final long serialVersionUID = 5667820033147334236L;
  
  private String JuryMemberId;
  private String JuryMemberName;
  private int evaluation;
  
}
