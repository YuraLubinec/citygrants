package com.warmcity.citygrants.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class BudgetDTO implements Serializable {

  private static final long serialVersionUID = -2734849621679578962L;

  private CostItemFeeDTO   costItemFee;
  private CostItemTransportDTO costItemTransport;
  private CostItemNutritionDTO costItemNutrition;
  private CostItemRentDTO costItemRent;
  private CostItemAdministrativeDTO costItemAdministrative;
  private CostItemAdvertisingDTO costItemAdvertising;
  @NotBlank
  @Size(max = 9)
  @Pattern(regexp = "\\d+")
  private String totalFromProgram;
  @NotBlank
  @Size(max = 9)
  @Pattern(regexp = "\\d+")
  private String totalFromOtherSources;

}
