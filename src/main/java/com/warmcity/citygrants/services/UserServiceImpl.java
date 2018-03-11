package com.warmcity.citygrants.services;

import com.warmcity.citygrants.dto.UserDTO;
import com.warmcity.citygrants.enums.Roles;
import com.warmcity.citygrants.models.User;
import com.warmcity.citygrants.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

  private static final String ROLE = "role";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Override
  public void createUser(User user) {

    // TODO add encoding
    userRepository.insert(user);
  }

  @Override
  public void saveUser(UserDTO userDTO) {

    // TODO handle how to work when field is empty?
    User user = convertToUser(userDTO);
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
    userDTO.setPassword(user.getPassword());
    userDTO.setFullName(user.getFullName());
    userDTO.setRole(user.getRole().getRole());

    return userDTO;
  }

  private User convertToUser(UserDTO userDTO ){
    User user = new User();
    user.setId(userDTO.getId());
    user.setLogin(userDTO.getLogin());
    user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
    user.setFullName(userDTO.getFullName());
    user.setRole(userDTO.getRole().equals(Roles.ADMIN.getRole()) ? Roles.ADMIN : Roles.JURYMEMBER);

    return user;
  }

  @Override
  public Map.Entry<String, String> getAuthority(Authentication authentication) {

    return new AbstractMap.SimpleEntry<String, String>(ROLE,
        authentication.getAuthorities().stream().findFirst().get().getAuthority());
  }

}
