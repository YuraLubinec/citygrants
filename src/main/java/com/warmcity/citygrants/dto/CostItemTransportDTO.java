package com.warmcity.citygrants.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class CostItemTransportDTO implements Serializable {

  private static final long serialVersionUID = -3353163063268971427L;
  
  @NotBlank
  @Size(max=50)
  private String description;
  @NotBlank
  @Size(max=4)
  @Pattern(regexp="\\d+")
  private String costForPerson;
  @NotBlank
  @Size(max=3)
  @Pattern(regexp="\\d+")
  private String numberOfPersons;
  @NotBlank
  @Size(max=6)
  @Pattern(regexp="\\d+")
  private String consumptionsFromProgram;
  @NotBlank
  @Size(max=6)
  @Pattern(regexp="\\d+")
  private String consumptionsFromOtherSources;
  
}
