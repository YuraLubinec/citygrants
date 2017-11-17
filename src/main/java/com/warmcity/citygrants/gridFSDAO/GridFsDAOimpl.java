package com.warmcity.citygrants.gridFSDAO;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.gridfs.GridFSDBFile;

@Repository
public class GridFsDAOimpl implements GridFsDAO {

  @Autowired
  private GridFsTemplate gridFsTemplate;

  @Override
  public void saveFile(InputStream inputStream, String name, String contentType, String id) {

    gridFsTemplate.store(inputStream, name, contentType, new BasicDBObject("project_id", id));
  }

  @Override
  public List<GridFSDBFile> find(String project_id, String contentType) {

    return gridFsTemplate.find(new Query(Criteria.where("metadata.project_id").is(project_id).and("contentType").is(contentType)));
  }

}
