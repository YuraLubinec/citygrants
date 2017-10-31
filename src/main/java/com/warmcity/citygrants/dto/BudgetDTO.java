package com.warmcity.citygrants.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BudgetDTO implements Serializable {

  private static final long serialVersionUID = -2734849621679578962L;

  @Valid
  @NotNull
  private List<CostItemDTO> costItemsFee;
  @Valid
  @NotNull
  private List<CostItemDTO> costItemsTransport;
  @Valid
  @NotNull
  private List<CostItemDTO> costItemsNutrition;
  @Valid
  @NotNull
  private List<CostItemDTO> costItemsRent;
  @Valid
  @NotNull
  private List<CostItemDTO> costItemsAdministrative;
  @Valid
  @NotNull
  private List<CostItemDTO> costItemsAdvertising;
  @Valid
  @NotNull
  private List<CostItemDTO> costItemsMaterial;
  @Valid
  @NotNull
  private List<CostItemDTO> costItemsOthers;

}
