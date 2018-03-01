package com.warmcity.citygrants.configuration.securityFilters;

import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.AUTHORITY;
import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.EXPIRATION_TIME;
import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.HEADER_AUTH;
import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.SECRET;
import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private static final int FIRST = 0;
  private static final String PASSWORD = "password";
  private static final String LOGIN = "login";
  public static final String ACCESS_CONTROL_EXPOSE_HEADERS = "Access-Control-Expose-Headers";

  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException {

    return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(req.getParameter(LOGIN),
        req.getParameter(PASSWORD), new ArrayList<>()));

  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
      Authentication auth) throws IOException, ServletException {

    res.addHeader(HEADER_AUTH, TOKEN_PREFIX + prepareToken(auth));
    res.addHeader(ACCESS_CONTROL_EXPOSE_HEADERS, HEADER_AUTH);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException {

    response.setStatus(HttpServletResponse.SC_OK);
  }

  private String prepareToken(Authentication auth) {

    Claims claims = Jwts.claims()
        .setSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
    claims.put(AUTHORITY,
        auth.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()).get(FIRST));

    return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET.getBytes()).compact();
  }

}
