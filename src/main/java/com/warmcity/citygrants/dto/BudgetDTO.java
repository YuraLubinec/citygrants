package com.warmcity.citygrants.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BudgetDTO implements Serializable {

  private static final long serialVersionUID = -2734849621679578962L;

  @NotNull
  private List<CostItemFeeDTO> costItemFee;
  @NotNull
  private List<CostItemTransportDTO> costItemTransport;
  @NotNull
  private List<CostItemNutritionDTO> costItemNutrition;
  @NotNull
  private List<CostItemRentDTO> costItemRent;
  @NotNull
  private List<CostItemAdministrativeDTO> costItemAdministrative;
  @NotNull
  private List<CostItemAdvertisingDTO> costItemAdvertising;
  @NotNull
  private List<CostItemMaterialDTO> costItemMaterial;

}
