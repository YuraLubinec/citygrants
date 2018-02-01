package com.warmcity.citygrants.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

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
  private String coordinatorName;
  private String coordinatorPhone;
  private String coordinatorEmail;
  private String projectMembers;
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
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
  private Date date;

}
