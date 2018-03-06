package com.warmcity.citygrants.configuration;

import com.warmcity.citygrants.configuration.securityFilters.JWTAuthenticationFilter;
import com.warmcity.citygrants.configuration.securityFilters.JWTAuthorizationFilter;
import com.warmcity.citygrants.services.userDetails.UserDtlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.SIGN_UP_URL;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final String ANTPATTERNOPTIONS = "/**";
  private static final String[] ANTPATTERNCLIENTPOST = { "/client/project", "/client/project/file" };

  @Autowired
  private UserDtlsService dtlsService;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {

    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(dtlsService).passwordEncoder(bCryptPasswordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
        .anyRequest().authenticated().and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
        .addFilter(new JWTAuthorizationFilter(authenticationManager())).sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  public void configure(WebSecurity web) throws Exception {

    web.ignoring().antMatchers(HttpMethod.OPTIONS, ANTPATTERNOPTIONS);
    web.ignoring().antMatchers(HttpMethod.POST, ANTPATTERNCLIENTPOST);
  }

}
