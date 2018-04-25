package com.warmcity.citygrants.validators;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.warmcity.citygrants.dto.AttachmentsDTO;

@Component
public class AttachmentsValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {

    return AttachmentsDTO.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    AttachmentsDTO attachments = (AttachmentsDTO) target;
    List<MultipartFile> images = attachments.getImages();
    List<MultipartFile> pdfs = attachments.getPdfDocs();
    if (images != null && !images.isEmpty()) {
      for (MultipartFile image : images) {
        String contentType = image.getContentType();
        if (!(contentType.equals(MediaType.IMAGE_JPEG_VALUE) || contentType.equals(MediaType.IMAGE_PNG_VALUE))) {
          errors.rejectValue("images", "not.valid.data.type", "Not right format, should be jpeg or png");
          break;
        }
      }
    }
    if (pdfs != null && !pdfs.isEmpty()) {
      for (MultipartFile pdf : pdfs) {
        String contentType = pdf.getContentType();
        if (!contentType.equals(MediaType.APPLICATION_PDF_VALUE)) {
          errors.rejectValue("images", "not.valid.data.type", "Not right format, should be PDF");
        }
      }
    }
  }

}
