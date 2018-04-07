package com.warmcity.citygrants.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;
import com.warmcity.citygrants.dto.AttachmentsDTO;
import com.warmcity.citygrants.gridFSDAO.GridFsDAOimpl;
import com.warmcity.citygrants.models.FileInfo;

import lombok.SneakyThrows;

@Service
public class UploadingServiceImpl implements UploadingService {

  @Autowired
  private GridFsDAOimpl gridFsDAOimpl;

  @Override
  @SneakyThrows
  public void uploadFilesToDb(AttachmentsDTO attachments) {
    
    String id = attachments.getId();
    List<MultipartFile> images = attachments.getImages();
    List<MultipartFile> pdfDocuments = attachments.getPdfDocs();

    if (images != null && !images.isEmpty()) {
      for (MultipartFile image : images) {
        gridFsDAOimpl.saveFile(image.getInputStream(), image.getOriginalFilename(), image.getContentType(), id);
      }
    }
    
    if (pdfDocuments != null && !pdfDocuments.isEmpty()) {
      for (MultipartFile pdfDocument : pdfDocuments) {
        gridFsDAOimpl.saveFile(pdfDocument.getInputStream(), pdfDocument.getOriginalFilename(),
            pdfDocument.getContentType(), id);
      }
    }

  }

  @Override
  public List<FileInfo> getAllFilesInfoForProject(String projectId) {

    return gridFsDAOimpl.findAllByProjectID(projectId).stream()
        .map(file -> new FileInfo(file.getId().toString(), file.getFilename())).collect(Collectors.toList());
  }

  @Override
  public Resource getFileById(String id) {

    GridFSDBFile file = gridFsDAOimpl.findOneById(id);
    return file != null ? new GridFsResource(file) : null;
  }

}
