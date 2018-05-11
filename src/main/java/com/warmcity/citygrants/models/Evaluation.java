package com.warmcity.citygrants.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Evaluation implements Serializable {

  private static final long serialVersionUID = 387286734166830545L;

  private String juryMemberId;
  private String juryMemberLogin;
  private String juryMemberFullName;
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

  public String getJuryMemberLogin() {
    return juryMemberLogin == null ? "" : this.juryMemberLogin;
  }

}
