package com.warmcity.citygrants.dto;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AttachmentsDTO implements Serializable {

  private static final long serialVersionUID = -3323489405201576647L;

  @NotBlank
  private String id;
  private List<MultipartFile> images;
  private List<MultipartFile> pdfDocs;

}
