package com.warmcity.citygrants.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class CostItemTransport implements Serializable {

  private static final long serialVersionUID = 3917217662752008000L;
  
  private String description;
  private int costForPerson;
  private int numberOfPersons;
  private int consumptionsFromProgram;
  private int consumptionsFromOtherSources;
  private int totalFromProgram;
  private int totalFromOtherSources;
  
}
