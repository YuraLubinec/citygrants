package com.warmcity.citygrants.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class UserDTO implements Serializable {
  
  private static final long serialVersionUID = -8706625097760523097L;
  
  private String id;
  @NotBlank
  private String login;
  @NotBlank
  private String password;
  @NotBlank
  private String fullName;
  @NotBlank
  private String role;
}
