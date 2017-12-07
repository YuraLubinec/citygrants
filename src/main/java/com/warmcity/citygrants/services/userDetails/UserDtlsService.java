package com.warmcity.citygrants.services.userDetails;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.warmcity.citygrants.models.User;
import com.warmcity.citygrants.repositories.UserRepository;

@Service
public class UserDtlsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    
    final User user = userRepository.findOneByLogin(login);

    if (user == null) {
      throw new UsernameNotFoundException(login);
    }
    return new org.springframework.security.core.userdetails.User(login, user.getPassword(), true, true, true, true,
        Arrays.asList(new SimpleGrantedAuthority(user.getRole().toString())));
  }




}
