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
  private List<CostItemFeeDTO> costItemFee;
  @Valid
  @NotNull
  private List<CostItemTransportDTO> costItemTransport;
  @Valid
  @NotNull
  private List<CostItemNutritionDTO> costItemNutrition;
  @Valid
  @NotNull
  private List<CostItemRentDTO> costItemRent;
  @Valid
  @NotNull
  private List<CostItemAdministrativeDTO> costItemAdministrative;
  @Valid
  @NotNull
  private List<CostItemAdvertisingDTO> costItemAdvertising;
  @Valid
  @NotNull
  private List<CostItemMaterialDTO> costItemMaterial;

}
