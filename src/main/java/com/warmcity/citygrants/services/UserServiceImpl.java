package com.warmcity.citygrants.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.warmcity.citygrants.dto.UserDTO;
import com.warmcity.citygrants.models.User;
import com.warmcity.citygrants.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void createUser(User user) {

    //TODO add encoding 
    userRepository.insert(user);
  }

  @Override
  public void saveUser(User user) {

    //TODO handle how to work when field is empty?
    if (userRepository.exists(user.getId())) {
      userRepository.save(user);
    }
  }

  @Override
  public void deleteUser(String id) {

    userRepository.delete(id);
  }

  @Override
  public List<UserDTO> getAllUsers() {

    return userRepository.findAll().stream().map(user -> convertToUserDTO(user)).collect(Collectors.toList());
  }

  @Override
  public UserDTO getUserById(String id) {

    return convertToUserDTO(userRepository.findOne(id));
  }

  @Override
  public UserDTO getUserByLogin(String login) {

    return convertToUserDTO(userRepository.findOneByLogin(login));

  }

  private UserDTO convertToUserDTO(User user) {

    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setLogin(user.getLogin());
    userDTO.setFullName(user.getFullName());
    return userDTO;
  }

}
