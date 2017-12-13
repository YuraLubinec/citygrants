package com.warmcity.citygrants.models;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "fs.files")
public class FileInfo implements Serializable {

  private static final long serialVersionUID = -316330781532510339L;

  @Id
  private String id;
  private FileMetadata metadata;
  private String filename;
  private String contentType;

}
