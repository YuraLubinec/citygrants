package com.warmcity.citygrants.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class CostItemAdministrativeDTO implements Serializable {

  private static final long serialVersionUID = -7647802974892227505L;

  @NotBlank
  @Size(max = 50)
  private String description;
  @NotBlank
  @Size(max = 7)
  @Pattern(regexp = "\\d+")
  private String cost;
  @NotBlank
  @Size(max = 3)
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
