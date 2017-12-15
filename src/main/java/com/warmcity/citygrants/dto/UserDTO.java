package com.warmcity.citygrants.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserDTO implements Serializable {
  
  private static final long serialVersionUID = -8706625097760523097L;
  
  private String id;
  private String login;
  private String fullName;
  

}
