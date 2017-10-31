package com.warmcity.citygrants.dto;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class DescriptionDTO implements Serializable {
  
  private static final long serialVersionUID = 8195797307456560831L;

  @NotBlank
  @Size(max=250)
  private String name;
  @NotBlank
  @Size(max=7)
  @Pattern(regexp = "[^0]\\d+")
  private String requestedBudget;
  @NotBlank
  @Size(max=250)
  private String organizationName;
  @NotBlank
  @Size(max=250)
  private String theme;
  @NotBlank
  @Size(max=100)
  private String requiredTime;
  @NotBlank
  @Size(max=100)
  private String coordinatorName;
  @NotBlank
  @Size(min=10, max=13)
  @Pattern(regexp="\\+?\\d+")
  private String coordinatorPhone;
  @NotBlank
  @Email
  @Size(max=50)
  private String coordinatorEmail;
  @NotBlank
  @Size(max=1000)
  private String projectMembers;
  @NotBlank
  @Size(max=1000)
  private String expirienceDescription;
  @NotBlank
  @Size(max=250)
  private String address;
  @NotBlank
  @Size(max=150)
  private String webaddress;
  @NotBlank
  @Size(max=1000)
  private String goal;
  @NotBlank
  @Size(max=2000)
  private String actuality;
  @NotBlank
  @Size(max=2000)
  private String fullDescription;
  @NotBlank
  @Size(max=2000)
  private String targetGroup;
  @NotBlank
  @Size(max=2000)
  private String expectedResults;
  @NotBlank
  @Size(max=1000)
  private String requiredPermissions;
  @NotBlank
  @Size(max=1000)
  private String partners;
}
