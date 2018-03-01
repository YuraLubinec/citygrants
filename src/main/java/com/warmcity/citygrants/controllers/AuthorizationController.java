package com.warmcity.citygrants.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.warmcity.citygrants.services.UserService;

@RestController
public class AuthorizationController {

  @Autowired
  private UserService userService;

  @GetMapping("/authority")
  public ResponseEntity<Map.Entry<String, String>> saveProject(Authentication authentication) {

    return new ResponseEntity<Map.Entry<String, String>>(userService.getAuthority(authentication), HttpStatus.OK);

  }

}
