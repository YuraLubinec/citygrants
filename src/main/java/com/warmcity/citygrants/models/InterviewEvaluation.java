package com.warmcity.citygrants.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class InterviewEvaluation implements Serializable {
  private static final long serialVersionUID = -616350751542510230L;

  private String juryMemberId;
  private String juryMemberName;
  private int evaluation;
}
