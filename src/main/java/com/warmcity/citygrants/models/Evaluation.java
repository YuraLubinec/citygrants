package com.warmcity.citygrants.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Evaluation implements Serializable {

  private static final long serialVersionUID = 387286734166830545L;

  private String juryMemberId;
  private String juryMemberName;
  private int evalActual;
  private int evalIntelligibility;
  private int evalCompetence;
  private int evalStability;
  private int evalEfficiency;
  private int evalInnovation;
  private int evalAttracting;
  private int evalParticipation;

  public String getJuryMemberId() {
    return juryMemberId == null ? "" : this.juryMemberId;
  }

  public String getJuryMemberName() {
    return juryMemberName == null ? "" : this.juryMemberName;
  }

}
