package com.warmcity.citygrants.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.warmcity.citygrants.models.User;

public interface UserRepository extends MongoRepository<User, String> {

  User findOneByLogin(String login);

}
