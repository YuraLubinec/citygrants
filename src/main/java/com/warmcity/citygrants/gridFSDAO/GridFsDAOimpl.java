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
  public GridFSDBFile findOneById(String id) {

    return gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id)));

  }

  @Override
  public List<GridFSDBFile> findAllByProjectID(String projectId) {
    
    return gridFsTemplate.find(new Query(Criteria.where("metadata.project_id").is(projectId)));

  }

  @Override
  public void deleteAllByProjectId(String id) {

    gridFsTemplate.delete(new Query(Criteria.where("metadata.project_id").is(id)));

  }

  @Override
  public void deleteOneById(String id) {

    gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));

  }
}
