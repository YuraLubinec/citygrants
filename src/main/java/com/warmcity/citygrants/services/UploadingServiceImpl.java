package com.warmcity.citygrants.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.warmcity.citygrants.dto.AttachmentsDTO;
import com.warmcity.citygrants.gridFSDAO.GridFsDAOimpl;
import com.warmcity.citygrants.models.FileInfo;
import com.warmcity.citygrants.repositories.FileInfoRepository;

import lombok.SneakyThrows;

@Service
public class UploadingServiceImpl implements UploadingService {

  @Autowired
  private GridFsDAOimpl gridFsDAOimpl;

  @Autowired
  private FileInfoRepository infoRepository;

  @Override
  public void uploadFilesToDb(AttachmentsDTO attachments) {

    saveImages(attachments.getImages(), attachments.getId());
    savePdfDocuments(attachments.getPdfDocs(), attachments.getId());
  }

  @Override
  public List<FileInfo> getAllFilesInfoForProject(String projectId) {

    return infoRepository.findByMetadataProjectId(projectId);
  }

  @Override
  public Resource getFileById(String id) {
   
    return new InputStreamResource(gridFsDAOimpl.findOneById(id).getInputStream());
  }

  @SneakyThrows
  private void saveImages(List<MultipartFile> images, String id) {

    if (images != null && !images.isEmpty()) {
      for (MultipartFile image : images) {
        gridFsDAOimpl.saveFile(image.getInputStream(), image.getOriginalFilename(), image.getContentType(), id);
      }
    }
  }

  @SneakyThrows
  private void savePdfDocuments(List<MultipartFile> pdfDocuments, String id) {

    if (pdfDocuments != null && !pdfDocuments.isEmpty()) {
      for (MultipartFile pdfDocument : pdfDocuments) {
        gridFsDAOimpl.saveFile(pdfDocument.getInputStream(), pdfDocument.getOriginalFilename(),
            pdfDocument.getContentType(), id);
      }
    }
  }

}
