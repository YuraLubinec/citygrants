package com.warmcity.citygrants.services;

import java.io.InputStream;
import java.util.List;

import org.springframework.core.io.Resource;

import com.warmcity.citygrants.dto.AttachmentsDTO;
import com.warmcity.citygrants.models.FileInfo;

public interface UploadingService {

  void uploadFilesToDb(AttachmentsDTO attachments);
  
  List <FileInfo> getAllFilesInfoForProject(String projectId);
  
  Resource getFileById(String id);

}
