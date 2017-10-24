package com.warmcity.citygrants.models;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Budget implements Serializable {

  private static final long serialVersionUID = 8142881569472211875L;
  
  private List<CostItem> costItemsFee;
  private int totalFeeFromProgram;
  private int totalFeeFromOtherSources;
  private List<CostItem> costItemsTransport;
  private int totalTransportFromProgram;
  private int totalTransportFromOtherSources;
  private List<CostItem> costItemsNutrition;
  private int totalNutritionFromProgram;
  private int totalNutritionFromOtherSources;
  private List<CostItem> costItemsRent;
  private int totalRentFromProgram;
  private int totalRentFromOtherSources;
  private List<CostItem> costItemsAdministrative;
  private int totalAdministrativeFromProgram;
  private int totalAdministrativeFromOtherSources;
  private List<CostItem> costItemsAdvertising;
  private int totalAdvertisingFromProgram;
  private int totalAdvertisingFromOtherSources;
  private List<CostItem> costItemsMaterial;
  private int totalMaterialsFromProgram;
  private int totalMaterialsFromOtherSources;
  private int totalFromProgram;
  private int totalFromOtherSources;
  
}
