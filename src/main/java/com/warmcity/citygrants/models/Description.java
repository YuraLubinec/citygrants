package com.warmcity.citygrants.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Description implements Serializable {

  private static final long serialVersionUID = -749688326971251288L;

  private String name;
  private int requestedBudget;
  private String organizationName;
  private String theme;
  private String requiredTime;
  private List<ProjectMember> projectMembers;
  private String expirienceDescription;
  private String address;
  private String webaddress;
  private String goal;
  private String actuality;
  private String fullDescription;
  private String targetGroup;
  private String expectedResults;
  private String requiredPermissions;
  private String partners;
  private Date date;

}
