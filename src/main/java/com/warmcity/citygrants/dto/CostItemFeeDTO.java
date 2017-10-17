package com.warmcity.citygrants.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class CostItemFeeDTO implements Serializable {

  private static final long serialVersionUID = -1975312402425350905L;
  
  @NotBlank
  @Size(max = 50)
  private String title;
  @NotBlank
  @Size(max = 5)
  @Pattern(regexp = "\\d+")
  private String monthAward;
  @NotBlank
  @Size(max = 5)
  @Pattern(regexp = "\\d+")
  private String timeSpent;
  @NotBlank
  @Size(max = 7)
  @Pattern(regexp = "\\d+")
  private String consumptionsFromProgram;
  @NotBlank
  @Size(max = 7)
  @Pattern(regexp = "\\d+")
  private String consumptionsFromOtherSources;
  @NotBlank
  @Size(max = 8)
  @Pattern(regexp = "\\d+")
  private String totalFromProgram;
  @NotBlank
  @Size(max = 8)
  @Pattern(regexp = "\\d+")
  private String totalFromOtherSources;

}
