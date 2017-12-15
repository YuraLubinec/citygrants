package com.warmcity.citygrants.models;

import java.io.Serializable;

import lombok.Data;

@Data

public class FileInfo implements Serializable {

  private static final long serialVersionUID = -316330781532510339L;

  private String id;
  private String filename;

  public FileInfo() {
  
  }

  public FileInfo(String id, String filename) {
    this.id = id;
    this.filename = filename;
  }

}
