package com.warmcity.citygrants.services;

import com.warmcity.citygrants.dto.AttachmentsDTO;

public interface UploadingService {

  void uploadFilesToDb(AttachmentsDTO attachments);

}
