package com.warmcity.citygrants.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ProjectApplicationDTO implements Serializable {

  private static final long serialVersionUID = -8779430168266035635L;

  @NotNull
  private DescriptionDTO description;
  @NotNull
  private BudgetDTO budget;
  @NotNull
  private boolean confirmed;

}
