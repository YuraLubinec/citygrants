package com.warmcity.citygrants.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class CostItemMaterialDTO implements Serializable {

  private static final long serialVersionUID = -6464098776304590873L;
  
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
  private String count;
  @NotBlank
  @Size(max = 7)
  @Pattern(regexp = "\\d+")
  private String consumptionsFromProgram;
  @NotBlank
  @Size(max = 7)
  @Pattern(regexp = "\\d+")
  private String consumptionsFromOtherSources;
}
