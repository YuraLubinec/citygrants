package com.warmcity.citygrants.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.warmcity.citygrants.models.FileInfo;

public interface FileInfoRepository extends MongoRepository<FileInfo, String> {
  
  List<FileInfo> findByMetadataProjectId(String project);

}
