package com.warmcity.citygrants.gridFSDAO;

import java.io.InputStream;

import com.mongodb.gridfs.GridFSDBFile;

public interface GridFsDAO {
  
  void saveFile(InputStream inputStream, String name, String contentType, String project_id);
  
  GridFSDBFile findOneById(String id);

}
