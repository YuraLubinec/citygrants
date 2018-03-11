package com.warmcity.citygrants.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDTO implements Serializable {
  
  private static final long serialVersionUID = -8706625097760523097L;
  
  private String id;
  private String login;
  private String password;
  private String fullName;
  private String role;
}
