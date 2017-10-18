package com.warmcity.citygrants.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class CostItemRent implements Serializable {

  private static final long serialVersionUID = 5930569958951134673L;
  
  private String description;
  private int costForDay;
  private int timeSpent;
  private int consumptionsFromProgram;
  private int consumptionsFromOtherSources;
  
}
