package com.warmcity.citygrants.models;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.warmcity.citygrants.enums.Roles;

import lombok.Data;

@Data
@Document(collection="users")
public class User implements Serializable {

  private static final long serialVersionUID = 5865568356057566633L;
  
  @Id
  private String id;
  @Size(min=4, max=15)
  private String login;
  @Size(min=8, max=15)
  private String password;
  @NotBlank
  @Size(max=25)
  private String fullName;
  @NotNull
  private Roles role;

}
