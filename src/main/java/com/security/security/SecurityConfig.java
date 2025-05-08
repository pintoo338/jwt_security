package com.security.security;

import com.security.jwt.AuthEntryPointJwt;
import com.security.jwt.AuthTokenfilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthTokenfilter authenticationJwtTokenFilter() {
        return new AuthTokenfilter();
    }


    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) ->
                requests.requestMatchers("/home/public").permitAll()
                        .requestMatchers("/home/admin/**").permitAll()
                        .requestMatchers("/home/user/**").permitAll()
                        .anyRequest().authenticated());
        http.sessionManagement(session
                -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.formLogin(withDefaults());
        http.csrf(csrf -> csrf.disable());
        http.addFilterBefore(authenticationJwtTokenFilter(),
                UsernamePasswordAuthenticationFilter.class);

        http.httpBasic(withDefaults());
        return http.build();
    }


    /*  @Bean
      public UserDetailsService userDetailsService()
      {
          UserDetails user1 = User.withUsername("client")
                  .password(passwordEncoder().encode("client"))
                  .roles("USER")
                  .build();

          UserDetails admin = User.withUsername("admin")
                  .password(passwordEncoder().encode("admin"))
                  .roles("ADMIN")
                  .build();
          JdbcUserDetailsManager userDetailsManager
                  = new JdbcUserDetailsManager(dataSource);
          userDetailsManager.createUser(user1);
          userDetailsManager.createUser(admin);
          return userDetailsManager;

         *//* return new InMemoryUserDetailsManager(user1,admin);*//*

    }*/
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder()); // Uses BCrypt
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

}
