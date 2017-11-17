package com.warmcity.citygrants.gridFSDAO;

import java.io.InputStream;
import java.util.List;

import com.mongodb.gridfs.GridFSDBFile;

public interface GridFsDAO {
  
  void saveFile(InputStream inputStream, String name, String contentType, String project_id);
  
  List<GridFSDBFile> find(String project_id, String contentType);

}
