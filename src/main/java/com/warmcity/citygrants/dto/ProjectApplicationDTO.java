package com.warmcity.citygrants.dto;

import java.io.Serializable;

import com.warmcity.citygrants.models.Budget;
import com.warmcity.citygrants.models.Description;

import lombok.Data;

@Data
public class ProjectApplicationDTO implements Serializable {

  private static final long serialVersionUID = -8779430168266035635L;

  private Description description;
  private Budget budget;
  private boolean confirmed;

}
