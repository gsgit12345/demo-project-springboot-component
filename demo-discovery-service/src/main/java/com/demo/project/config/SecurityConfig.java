package com.demo.project.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity

public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    //@Value("${eureka.username}")
    String username="ghanshyam";
   // @Value("${eureka.password}")
    String password="password";


    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
      //  System.out.println("password:" + password + "user:=" + username);
        authenticationManagerBuilder.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser(username).password(password).authorities("USER");

    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().
                authorizeRequests().anyRequest().
                authenticated().and().httpBasic();

    }

}