package com.warmcity.citygrants.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document
public class FileMetadata implements Serializable {

  private static final long serialVersionUID = 1206349008870200069L;
  
  @Field("project_id")
  private String projectId;

}
