package com.warmcity.citygrants.services;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.warmcity.citygrants.dto.UserDTO;
import com.warmcity.citygrants.enums.Roles;
import com.warmcity.citygrants.models.User;
import com.warmcity.citygrants.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

  private static final String ROLE = "role";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void createUser(UserDTO userDTO) {

    userRepository.insert(prepareNewUser(userDTO));
  }

  @Override
  public void saveUser(UserDTO userDTO) {

    User dbUser = userRepository.findOne(userDTO.getId());
    if (dbUser != null) {
      updateExistingUserInfo(userDTO, dbUser);
      userRepository.save(dbUser);
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

  @Override
  public Map.Entry<String, String> getAuthority(Authentication authentication) {

    return new AbstractMap.SimpleEntry<String, String>(ROLE,
        authentication.getAuthorities().stream().findFirst().get().getAuthority());
  }

  private UserDTO convertToUserDTO(User user) {

    UserDTO userDTO = new UserDTO();
    userDTO.setId(user.getId());
    userDTO.setLogin(user.getLogin());
    userDTO.setFullName(user.getFullName());
    userDTO.setRole(user.getRole().getRole());
    return userDTO;
  }

  private User prepareNewUser(UserDTO userDTO) {

    User user = new User();
    user.setLogin(userDTO.getLogin());
    user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
    user.setFullName(userDTO.getFullName());
    user.setRole(Roles.valueOf(userDTO.getRole()));
    return user;
  }

  private void updateExistingUserInfo(UserDTO dto, User existingUser) {

    String password = dto.getPassword();
    existingUser.setLogin(dto.getLogin());
    existingUser.setFullName(dto.getFullName());
    existingUser.setRole(Roles.valueOf(dto.getRole()));
    if (!password.isEmpty()) {
      existingUser.setPassword(bCryptPasswordEncoder.encode(password));
    }
  }

}
