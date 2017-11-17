package com.warmcity.citygrants.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProjectApplicationDTO implements Serializable {

  private static final long serialVersionUID = -8779430168266035635L;

  @Valid
  @NotNull
  private DescriptionDTO description;
  @Valid
  @NotNull
  private BudgetDTO budget;
  @NotNull
  private boolean confirmed;
  List<MultipartFile> images;
  List<MultipartFile> pdfDocuments;
  

}
