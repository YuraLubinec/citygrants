package com.warmcity.citygrants.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class CostItemNutritionDTO implements Serializable {

  private static final long serialVersionUID = -6168099374318388793L;

  @NotBlank
  @Size(max = 50)
  private String description;
  @NotBlank
  @Size(max = 5)
  @Pattern(regexp = "\\d+")
  private String costForPerson;
  @NotBlank
  @Size(max = 3)
  @Pattern(regexp = "\\d+")
  private String numberOfPersons;
  @NotBlank
  @Size(max = 5)
  @Pattern(regexp = "\\d+")
  private String consumptionsFromProgram;
  @NotBlank
  @Size(max = 5)
  @Pattern(regexp = "\\d+")
  private String consumptionsFromOtherSources;
  @NotBlank
  @Size(max = 6)
  @Pattern(regexp = "\\d+")
  private String totalFromProgram;
  @NotBlank
  @Size(max = 6)
  @Pattern(regexp = "\\d+")
  private String totalFromOtherSources;

}
