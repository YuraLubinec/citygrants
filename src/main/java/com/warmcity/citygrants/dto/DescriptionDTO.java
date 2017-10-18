package com.warmcity.citygrants.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class DescriptionDTO implements Serializable {
  
  private static final long serialVersionUID = 8195797307456560831L;

  @NotBlank
  @Size(max=50)
  private String name;
  @NotBlank
  @Size(max=50)
  @Pattern(regexp="\\d+")
  private String requestedBudget;
  @NotBlank
  @Size(max=50)
  private String organizationName;
  @NotBlank
  @Size(max=50)
  private String theme;
  @NotBlank
  @Size(max=30)
  private String requiredTime;
  @NotNull
  private List<ProjectMemberDTO> projectMembers;
  @NotBlank
  @Size(max=50)
  private String expirienceDescription;
  @NotBlank
  @Size(max=50)
  private String address;
  @NotBlank
  @Size(max=50)
  private String webaddress;
  @NotBlank
  @Size(max=150)
  private String goal;
  @NotBlank
  @Size(max=500)
  private String actuality;
  @NotBlank
  @Size(max=500)
  private String fullDescription;
  @NotBlank
  @Size(max=500)
  private String targetGroup;
  @NotBlank
  @Size(max=500)
  private String expectedResults;
  @NotBlank
  @Size(max=300)
  private String requiredPermissions;
  @NotBlank
  @Size(max=200)
  private String partners;
}
