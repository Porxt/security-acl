package com.security.acl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      //.csrf().disable()
      .authorizeRequests()
        .anyRequest()
          .authenticated()
      .and()
      .httpBasic();
  }

  // Available users
  @Override
  @Bean
  protected UserDetailsService userDetailsService() {
    UserDetails user = User.builder()
      .username("user")
      .password(passwordEncoder.encode("password"))
      .roles("USER")
      .build();

    UserDetails admin = User.builder()
      .username("admin")
      .password(passwordEncoder.encode("password"))
      .roles("ADMIN")
      .build();

      UserDetails editor = User.builder()
      .username("editor")
      .password(passwordEncoder.encode("password"))
      .roles("EDITOR")
      .build();
    
    return new InMemoryUserDetailsManager(user, admin, editor);
  }
}
