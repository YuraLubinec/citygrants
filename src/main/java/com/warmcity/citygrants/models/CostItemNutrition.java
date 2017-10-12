package com.warmcity.citygrants.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class CostItemNutrition implements Serializable {

  private static final long serialVersionUID = -827952910213418019L;
  
  private String description;
  private int costForPerson;
  private int numberOfPersons;
  private int consumptionsFromProgram;
  private int consumptionsFromOtherSources;
  private int totalFromProgram;
  private int totalFromOtherSources;
  
}
