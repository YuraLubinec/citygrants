package com.warmcity.citygrants.models;

import java.io.Serializable;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Evaluation implements Serializable {

  private String juryMemberId;
  private String juryMemberName;
  private int evalActual;
  private int evalIntelligibility;
  private int evalCompetence;
  private int evalStability;
  private int evalEfficiency;
  private int evalInnovation;
  private int evalAttracting;
  private int evalСooperation;

  public String getJuryMemberId(){
    return juryMemberId == null ? "" : this.juryMemberId;
  }

  public String getJuryMemberName(){
    return juryMemberName == null ? "" : this.juryMemberName;
  }

}
