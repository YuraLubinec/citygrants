package com.warmcity.citygrants.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class CostItemAdministrative implements Serializable {

  private static final long serialVersionUID = 2648926666498956911L;
  
  private String description;
  private int cost;
  private int timeSpent;
  private int consumptionsFromProgram;
  private int consumptionsFromOtherSources;
  private int totalFromProgram;
  private int totalFromOtherSources;
  
}
