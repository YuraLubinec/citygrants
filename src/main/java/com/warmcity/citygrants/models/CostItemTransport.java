package com.warmcity.citygrants.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class CostItemTransport implements Serializable {

  private static final long serialVersionUID = 3917217662752008000L;
  
  private String description;
  private int costForPerson;
  private int numberOfPersons;
  private int consumptionsFromProgram;
  private int consumptionsFromOtherSources;
  
}
