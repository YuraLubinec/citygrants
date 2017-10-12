package com.warmcity.citygrants.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.warmcity.citygrants.models.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {

}
