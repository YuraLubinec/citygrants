package com.warmcity.citygrants.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.warmcity.citygrants.models.CostItemAdministrative;
import com.warmcity.citygrants.models.CostItemAdvertising;
import com.warmcity.citygrants.models.CostItemFee;
import com.warmcity.citygrants.models.CostItemNutrition;
import com.warmcity.citygrants.models.CostItemRent;
import com.warmcity.citygrants.models.CostItemTransport;

import lombok.Data;

@Data
public class BudgetDTO implements Serializable {

  private static final long serialVersionUID = -2734849621679578962L;
  
  private CostItemFee costItemFee;
  private CostItemTransport costItemTransport;
  private CostItemNutrition costItemNutrition;
  private CostItemRent costItemRent;
  private CostItemAdministrative costItemAdministrative;
  private CostItemAdvertising costItemAdvertising;
  @NotBlank
  @Size(max=50)
  @Pattern(regexp="\\d+")
  private String totalFromProgram;
  @NotBlank
  @Size(max=50)
  @Pattern(regexp="\\d+")
  private String totalFromOtherSources;
  
  
}
