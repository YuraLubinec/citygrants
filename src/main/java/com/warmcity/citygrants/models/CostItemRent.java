package com.warmcity.citygrants.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class CostItemRent implements Serializable {

  private static final long serialVersionUID = 5930569958951134673L;
  
  private String description;
  private int costForPerson;
  private int timeSpent;
  private int consumptionsFromProgram;
  private int consumptionsFromOtherSources;
  
}
