package com.warmcity.citygrants.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Comment implements Serializable {

  private static final long serialVersionUID = 6033746538948239725L;
  
  private String userId;
  private String username;
  private String text;

}
