package com.warmcity.citygrants.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class CostItemNutrition implements Serializable {

  private static final long serialVersionUID = -827952910213418019L;
  
  private String description;
  private int costForPerson;
  private int numberOfPersons;
  private int consumptionsFromProgram;
  private int consumptionsFromOtherSources;
  
}
