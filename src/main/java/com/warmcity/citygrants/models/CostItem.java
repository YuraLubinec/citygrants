package com.warmcity.citygrants.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class CostItem implements Serializable {


  private static final long serialVersionUID = -7903971741682483015L;

  private String description;
  private String cost;
  private String count;
  private int consumptionsFromProgram;
  private int consumptionsFromOtherSources;
}
