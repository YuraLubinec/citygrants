package com.warmcity.citygrants.repositories;

import org.springframework.data.repository.Repository;

import com.warmcity.citygrants.models.User;

public interface UserRepository extends Repository<User, String> {

  User findOneByLogin(String login);

}
