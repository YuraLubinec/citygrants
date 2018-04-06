package com.warmcity.citygrants.serviceLayerUnitTests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.gridfs.GridFSDBFile;
import com.warmcity.citygrants.dto.AttachmentsDTO;
import com.warmcity.citygrants.gridFSDAO.GridFsDAOimpl;
import com.warmcity.citygrants.models.FileInfo;
import com.warmcity.citygrants.services.UploadingServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UploadingServiceTest {

  @Mock
  private GridFsDAOimpl gridFsDAOimpl;

  @InjectMocks
  private UploadingServiceImpl uploadingServiceImpl;

  @Test
  public void emptyAttachmentUploadFilesToDbTest() {

    AttachmentsDTO attachmentsDTO = new AttachmentsDTO();
    attachmentsDTO.setId(RandomStringUtils.randomAlphanumeric(5));
    attachmentsDTO.setImages(new ArrayList<MultipartFile>());
    attachmentsDTO.setPdfDocs(null);

    uploadingServiceImpl.uploadFilesToDb(attachmentsDTO);

    verify(gridFsDAOimpl, never()).saveFile(anyObject(), anyString(), anyString(), anyString());
  }

  @Test
  public void uploadFilesToDbTest() {

    Random random = new Random();
    int imagesCount = random.nextInt(5);
    int pdfsCount = random.nextInt(5);

    List<MultipartFile> images = new ArrayList<>();
    List<MultipartFile> pdfDocs = new ArrayList<>();

    for (int i = 0; i < imagesCount; i++) {
      images.add(new MockMultipartFile(RandomStringUtils.randomAlphabetic(5), new byte[5]));
    }
    for (int i = 0; i < pdfsCount; i++) {
      pdfDocs.add(new MockMultipartFile(RandomStringUtils.randomAlphabetic(5), new byte[5]));
    }

    AttachmentsDTO attachmentsDTO = new AttachmentsDTO();
    attachmentsDTO.setId(RandomStringUtils.randomAlphanumeric(5));
    attachmentsDTO.setImages(images);
    attachmentsDTO.setPdfDocs(pdfDocs);

    uploadingServiceImpl.uploadFilesToDb(attachmentsDTO);
    verify(gridFsDAOimpl, times(imagesCount + pdfsCount)).saveFile(anyObject(), anyString(), anyString(), anyString());
  }

  @Test
  public void getAllFilesInfoForProjectTest() {

    List<GridFSDBFile> dbFiles = new ArrayList<>();
    List<FileInfo> filesInfo = new ArrayList<>();

    String id = RandomStringUtils.randomAlphanumeric(5);
    String file1Name = RandomStringUtils.randomAlphabetic(5);
    String file2Name = RandomStringUtils.randomAlphabetic(5);

    GridFSDBFile dbFile1 = new GridFSDBFile();
    dbFile1.put("_id", id);
    dbFile1.put("filename", file1Name);

    GridFSDBFile dbFile2 = new GridFSDBFile();
    dbFile2.put("_id", id);
    dbFile2.put("filename", file2Name);

    FileInfo file1Info = new FileInfo();
    file1Info.setId(id);
    file1Info.setFilename(file1Name);

    FileInfo file2Info = new FileInfo();
    file2Info.setId(id);
    file2Info.setFilename(file2Name);

    dbFiles.add(dbFile1);
    dbFiles.add(dbFile2);

    filesInfo.add(file1Info);
    filesInfo.add(file2Info);

    Mockito.when(gridFsDAOimpl.findAllByProjectID(anyString())).thenReturn(dbFiles);
    assertEquals(uploadingServiceImpl.getAllFilesInfoForProject(id), filesInfo);

  }

  @Test
  public void getInfoForProjectWhenNoFilesPresentTest() {

    Mockito.when(gridFsDAOimpl.findAllByProjectID(anyString())).thenReturn(new ArrayList<GridFSDBFile>());
    assertEquals(uploadingServiceImpl.getAllFilesInfoForProject(anyString()), new ArrayList<FileInfo>());
  }

  @Test
  public void getFileByIdTest() {
    // TODO implement test
  }

}
