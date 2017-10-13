package com.warmcity.citygrants.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class ProjectMemberDTO implements Serializable {

  private static final long serialVersionUID = -2884170363877647502L;

  @NotBlank
  @Size(min=10, max=12)
  @Pattern(regexp="\\+?\\d+")
  private String phone;
  @NotBlank
  @Email
  private String email;
  @NotBlank
  @Size(max=50)
  private String fullName;


}
