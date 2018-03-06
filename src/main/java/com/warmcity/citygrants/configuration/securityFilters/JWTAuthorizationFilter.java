package com.warmcity.citygrants.configuration.securityFilters;

import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.AUTHORITY;
import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.HEADER_AUTH;
import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.SECRET;
import static com.warmcity.citygrants.configuration.securityFilters.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {

    super(authenticationManager);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    String header = request.getHeader(HEADER_AUTH);
    if (header == null || !header.startsWith(TOKEN_PREFIX)) {
      chain.doFilter(request, response);
      return;
    }
    UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(header);
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    chain.doFilter(request, response);
  }

  private UsernamePasswordAuthenticationToken getAuthenticationToken(String header) {

    String login = null;
    List<GrantedAuthority> authorities = new ArrayList<>();
    try {
      Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(header.replace(TOKEN_PREFIX, ""))
          .getBody();
      authorities.add(new SimpleGrantedAuthority(claims.get(AUTHORITY, String.class)));
      login = claims.getSubject();
    } catch (ExpiredJwtException e) {
    }
    return login != null ? new UsernamePasswordAuthenticationToken(login, null, authorities) : null;

  }

}
