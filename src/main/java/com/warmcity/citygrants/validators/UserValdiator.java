package com.warmcity.citygrants.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.warmcity.citygrants.dto.UserDTO;
import com.warmcity.citygrants.repositories.UserRepository;

@Component
public class UserValdiator implements Validator {

  @Autowired
  private UserRepository repository;

  @Override
  public boolean supports(Class<?> clazz) {

    return UserDTO.class.equals(clazz);
  }

  @Override
  public void validate(Object target, Errors errors) {

    UserDTO dto = (UserDTO) target;
    if (repository.findOneByLogin(dto.getLogin()) != null) {
      errors.rejectValue("login", "user.login.not.unique", "Login not unique");
    }
    ;

  }

}
