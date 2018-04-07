package com.warmcity.citygrants.serviceLayerUnitTests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.warmcity.citygrants.dto.UserDTO;
import com.warmcity.citygrants.enums.Roles;
import com.warmcity.citygrants.models.User;
import com.warmcity.citygrants.repositories.UserRepository;
import com.warmcity.citygrants.services.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private Authentication authentication;

  @Spy
  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @InjectMocks
  private UserServiceImpl userServiceImpl;

  @Captor
  ArgumentCaptor<User> captor;

  @Test
  public void createUserTest() {

    UserDTO userDTO = new UserDTO();
    userDTO.setLogin(RandomStringUtils.randomAlphabetic(5));
    userDTO.setPassword(RandomStringUtils.randomNumeric(5));
    userDTO.setFullName(RandomStringUtils.randomAlphabetic(5));
    userDTO.setRole(Roles.ADMIN.toString());

    User user = new User();
    user.setLogin(userDTO.getLogin());
    user.setFullName(userDTO.getFullName());
    user.setRole(Roles.ADMIN);

    userServiceImpl.createUser(userDTO);
    verify(userRepository, times(1)).insert(captor.capture());

    assertEquals(captor.getValue().getLogin(), user.getLogin());
    assertEquals(captor.getValue().getFullName(), user.getFullName());
    assertEquals(captor.getValue().getRole(), user.getRole());
  };

  @Test
  public void saveUserTestWithPassword() {

    String password = RandomStringUtils.randomNumeric(5);

    UserDTO userDTO = new UserDTO();
    userDTO.setId(RandomStringUtils.randomAlphanumeric(5));
    userDTO.setLogin(RandomStringUtils.randomAlphabetic(5));
    userDTO.setPassword(password);
    userDTO.setFullName(RandomStringUtils.randomAlphabetic(5));
    userDTO.setRole(Roles.ADMIN.toString());

    User user = new User();
    user.setId(userDTO.getId());
    user.setLogin(userDTO.getLogin() + RandomStringUtils.randomAlphabetic(1));
    user.setFullName(userDTO.getFullName() + RandomStringUtils.randomAlphabetic(1));
    user.setPassword(bCryptPasswordEncoder.encode(password));
    user.setRole(Roles.JURYMEMBER);

    when(userRepository.findOne(anyString())).thenReturn(user);

    userServiceImpl.saveUser(userDTO);

    verify(userRepository, times(1)).save(captor.capture());
    assertEquals(captor.getValue().getId(), userDTO.getId());
    assertEquals(captor.getValue().getLogin(), userDTO.getLogin());
    assertEquals(captor.getValue().getFullName(), userDTO.getFullName());
    assertEquals(captor.getValue().getRole().toString(), userDTO.getRole());
    assertThat(captor.getValue().getPassword()).isNotBlank();
    assertNotEquals(captor.getValue().getPassword(), user.getPassword());
  };

  @Test
  public void saveUserTestWithoutPassword() {

    String password = RandomStringUtils.randomNumeric(5);

    UserDTO userDTO = new UserDTO();
    userDTO.setId(RandomStringUtils.randomAlphanumeric(5));
    userDTO.setLogin(RandomStringUtils.randomAlphabetic(5));
    userDTO.setPassword("");
    userDTO.setFullName(RandomStringUtils.randomAlphabetic(5));
    userDTO.setRole(Roles.ADMIN.toString());

    User user = new User();
    user.setId(userDTO.getId());
    user.setLogin(userDTO.getLogin() + RandomStringUtils.randomAlphabetic(1));
    user.setFullName(userDTO.getFullName() + RandomStringUtils.randomAlphabetic(1));
    user.setPassword(bCryptPasswordEncoder.encode(password));
    user.setRole(Roles.JURYMEMBER);

    when(userRepository.findOne(anyString())).thenReturn(user);

    userServiceImpl.saveUser(userDTO);

    verify(userRepository, times(1)).save(captor.capture());
    assertEquals(captor.getValue().getId(), userDTO.getId());
    assertEquals(captor.getValue().getLogin(), userDTO.getLogin());
    assertEquals(captor.getValue().getFullName(), userDTO.getFullName());
    assertEquals(captor.getValue().getRole().toString(), userDTO.getRole());
    assertThat(captor.getValue().getPassword()).isNotBlank();
    assertEquals(captor.getValue().getPassword(), user.getPassword());
  };

  @Test
  public void getAllUsersTest() {

    User user1 = new User();
    user1.setId(RandomStringUtils.randomAlphanumeric(5));
    user1.setLogin(RandomStringUtils.randomAlphabetic(5));
    user1.setFullName(RandomStringUtils.randomAlphabetic(5));
    user1.setPassword(RandomStringUtils.randomNumeric(5));
    user1.setRole(Roles.ADMIN);

    User user2 = new User();
    user2.setId(RandomStringUtils.randomAlphanumeric(8));
    user2.setLogin(RandomStringUtils.randomAlphabetic(5));
    user2.setFullName(RandomStringUtils.randomAlphabetic(5));
    user2.setPassword(RandomStringUtils.randomNumeric(5));
    user2.setRole(Roles.JURYMEMBER);

    UserDTO userDTO1 = new UserDTO();
    userDTO1.setId(user1.getId());
    userDTO1.setLogin(user1.getLogin());
    userDTO1.setFullName(user1.getFullName());
    userDTO1.setRole(user1.getRole().getRole());

    UserDTO userDTO2 = new UserDTO();
    userDTO2.setId(user2.getId());
    userDTO2.setLogin(user2.getLogin());
    userDTO2.setFullName(user2.getFullName());
    userDTO2.setRole(user2.getRole().getRole());

    List<User> users = Stream.of(user1, user2).collect(Collectors.toList());

    when(userRepository.findAll()).thenReturn(users);

    List<UserDTO> userDTOs = userServiceImpl.getAllUsers();

    assertTrue(userDTOs.size() == 2);
    assertThat(userDTOs).containsOnlyOnce(userDTO1, userDTO2);
  };

  @Test
  public void getAuthorityTest() {

    // use list just to keep order
    List<? extends GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(Roles.ADMIN.toString()),
        new SimpleGrantedAuthority(Roles.JURYMEMBER.toString()));

    doReturn(authorities).when(authentication).getAuthorities();
    assertEquals(userServiceImpl.getAuthority(authentication),
        new AbstractMap.SimpleEntry<String, String>(UserServiceImpl.ROLE, Roles.ADMIN.toString()));
  };

}
