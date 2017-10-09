package com.warmcity.citygrants.models;

import java.io.Serializable;

import lombok.Data;

@Data
public class ProjectMember implements Serializable {

  private static final long serialVersionUID = 8777699849217748264L;
  
  private String phone;
  private String email;
  private String fullName;

}
