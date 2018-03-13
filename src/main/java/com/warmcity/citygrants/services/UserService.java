package com.warmcity.citygrants.services;

import com.warmcity.citygrants.dto.UserDTO;
import com.warmcity.citygrants.models.User;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Map.Entry;

public interface UserService {

  User createUser(UserDTO userDTO);

  void saveUser(UserDTO user);

  void deleteUser(String id);

  List<UserDTO> getAllUsers();

  UserDTO getUserById(String id);
  
  UserDTO getUserByLogin(String login);

  Entry<String, String> getAuthority(Authentication authentication);

}
