package com.example.hospital.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
      return new JdbcUserDetailsManager(dataSource);
    }
    //@Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(PasswordEncoder passwordEncoder) {
    String encodedPassword = passwordEncoder.encode("1234");
    System.out.println(encodedPassword);
    return new InMemoryUserDetailsManager(
      
      User.withUsername("user1").password(encodedPassword).roles("USER").build(),
      User.withUsername("user2").password(encodedPassword).roles("USER").build(),
      User.withUsername("admin").password(encodedPassword).roles("USER","ADMIN").build()
    );
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
       return httpSecurity
       .formLogin(fl->fl.loginPage("/login").defaultSuccessUrl("/").permitAll())
       .authorizeHttpRequests(ar->ar.requestMatchers("/webjars/**").permitAll())
       .rememberMe(Customizer.withDefaults())
       //.authorizeHttpRequests(ar->ar.requestMatchers("/admin/**").hasRole("ADMIN"))
       //.authorizeHttpRequests(ar->ar.requestMatchers("/user/**").hasRole("USER"))
       .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
       .exceptionHandling(eh->eh.accessDeniedPage("/notAuthorized"))
       .build();
    }
    
}
