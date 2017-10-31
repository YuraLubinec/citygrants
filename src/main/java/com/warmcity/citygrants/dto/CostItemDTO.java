package com.warmcity.citygrants.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class CostItemDTO implements Serializable {

  private static final long serialVersionUID = -1919332474178099202L;
  
  @NotBlank
  @Size(max = 100)
  private String description;
  @NotBlank
  @Size(max = 6)
  private String cost;
  @NotBlank
  @Size(max = 6)
  private String count;
  @NotBlank
  @Size(max = 6)
  @Pattern(regexp = "[^0]\\d+")
  private String consumptionsFromProgram;
  @NotBlank
  @Size(max = 6)
  @Pattern(regexp = "[^0]\\d+")
  private String consumptionsFromOtherSources;
  

}
