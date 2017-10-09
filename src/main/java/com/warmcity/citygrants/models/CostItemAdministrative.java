package com.warmcity.citygrants.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class CostItemAdministrative implements Serializable {

  private static final long serialVersionUID = 2648926666498956911L;
  
  private String description;
  private int costForPerson;
  private int timeSpent;
  private int consumptionsFromProgram;
  private int consumptionsFromOtherSources;
  
}
