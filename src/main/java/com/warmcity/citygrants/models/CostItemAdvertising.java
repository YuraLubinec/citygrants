package com.warmcity.citygrants.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class CostItemAdvertising implements Serializable {
  
  private static final long serialVersionUID = 6513711290786980976L;
  
  private String description;
  private int cost;
  private int time;
  private int consumptionsFromProgram;
  private int consumptionsFromOtherSources;
  private int totalFromProgram;
  private int totalFromOtherSources;

}
