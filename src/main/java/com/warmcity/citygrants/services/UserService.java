package com.warmcity.citygrants.services;

import java.util.List;

import com.warmcity.citygrants.dto.UserDTO;
import com.warmcity.citygrants.models.User;

public interface UserService {

  void createUser(User user);

  void saveUser(User user);

  void deleteUser(String id);

  List<UserDTO> getAllUsers();

  UserDTO getUserById(String id);
  
  UserDTO getUserByLogin(String login);

}
