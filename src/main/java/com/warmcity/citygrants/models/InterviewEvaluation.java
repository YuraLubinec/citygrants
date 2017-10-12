package com.warmcity.citygrants.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data 
@Document
public class InterviewEvaluation implements Serializable {

  private static final long serialVersionUID = 5667820033147334236L;
  
  private String JuryMemberId;
  private String JuryMemberName;
  private int evaluation;
  
}
