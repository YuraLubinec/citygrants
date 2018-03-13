package com.warmcity.citygrants.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.warmcity.citygrants.dto.UserDTO;
import com.warmcity.citygrants.models.User;
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
    User user = repository.findOneByLogin(dto.getLogin());
    String id = dto.getId();
    Boolean condition = user != null && (!StringUtils.isEmpty(id) && !id.equals(user.getId()) || StringUtils.isEmpty(id));
    if (condition) {
      errors.rejectValue("login", "user.login.not.unique", "Such login already existe");
    }

  }

}
