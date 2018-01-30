package com.warmcity.citygrants.models;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Comment implements Serializable {

  private static final long serialVersionUID = 6033746538948239725L;
  
  private String userId;
  private String userName;
  private String text;
  private Date date;

  public void setDate() {
    this.date = new Date();
  }
}
