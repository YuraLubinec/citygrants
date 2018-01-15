package com.warmcity.citygrants.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FileInfo implements Serializable {

  private static final long serialVersionUID = -316330781532510339L;

  private String id;
  private String filename;
}
