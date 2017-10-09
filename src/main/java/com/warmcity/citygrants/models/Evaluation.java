package com.warmcity.citygrants.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class Evaluation implements Serializable {

  private static final long serialVersionUID = 8391566704550860062L;
  
  private String JuryMemberId;
  private String JuryMemberName;
  private int evaluation;

}
