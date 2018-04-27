package com.warmcity.citygrants.integrationTests;

import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.AUTHORITY;
import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.EXPIRATION_TIME;
import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.HEADER_AUTH;
import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.SECRET;
import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.TOKEN_PREFIX;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.warmcity.citygrants.dto.UserDTO;
import com.warmcity.citygrants.enums.Roles;
import com.warmcity.citygrants.main.CitygrantsApplication;
import com.warmcity.citygrants.models.User;
import com.warmcity.citygrants.services.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CitygrantsApplication.class)
@AutoConfigureMockMvc
public class AuthorizationControllerTest {

  @Autowired
  private UserService userService;

  @Autowired
  private MockMvc mvc;

  @Test
  public void getAuthorityWithNoCredentialsTest() throws Exception {

    mvc.perform(get("/authority")).andExpect(status().is4xxClientError());
  }

  @Test
  public void getAuthorityTest() throws Exception {
    
    UserDTO userDTO = new UserDTO();
    userDTO.setLogin(RandomStringUtils.randomAlphabetic(5));
    userDTO.setPassword(RandomStringUtils.randomAlphabetic(5));
    userDTO.setRole(Roles.ADMIN.toString());
    userDTO.setFullName(RandomStringUtils.randomAlphabetic(5));
    User user = userService.createUser(userDTO);
    mvc.perform(get("/authority").header(HEADER_AUTH, prepareToken(user))).andDo(print())
        .andExpect(status().is2xxSuccessful());
  }

  private String prepareToken(User user) {
    Claims claims = Jwts.claims().setSubject(user.getLogin())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
    claims.put(AUTHORITY, user.getRole().toString());
    String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
    return TOKEN_PREFIX + token;
  }
}
