package com.warmcity.citygrants.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class CostItemFee implements Serializable {

  private static final long serialVersionUID = -651088044360183033L;
  
  private String title;
  private int monthAward;
  private int timeSpent;
  private int consumptionsFromProgram;
  private int consumptionsFromOtherSources;
  
}
