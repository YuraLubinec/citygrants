package com.warmcity.citygrants.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Budget implements Serializable {

  private static final long serialVersionUID = 8142881569472211875L;
  
  private CostItemFee costItemFee;
  private CostItemTransport costItemTransport;
  private CostItemNutrition costItemNutrition;
  private CostItemRent costItemRent;
  private CostItemAdministrative costItemAdministrative;
  private CostItemAdvertising costItemAdvertising;
  private int totalFromProgram;
  private int totalFromOtherSources;
  
}
