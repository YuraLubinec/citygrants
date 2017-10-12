package com.warmcity.citygrants.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class CostItemMaterials implements Serializable {

  private static final long serialVersionUID = 4777367484927168570L;

  private String description;
  private int cost;
  private int number;
  private int consumptionsFromProgram;
  private int consumptionsFromOtherSources;
  private int totalFromProgram;
  private int totalFromOtherSources;
  
}
