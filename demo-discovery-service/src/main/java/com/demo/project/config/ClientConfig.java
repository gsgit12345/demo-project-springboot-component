package com.demo.project.config;

//@Configuration
//@EnableWebSecurity
public class ClientConfig //extends WebSecurityConfigurerAdapter {
{

    //@Value("${eureka.username}")
    //  String username;
    //@Value("${eureka.password}")
    //String password;
/*

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        System.out.println("password:" + password + "user:=" + username);
        authenticationManagerBuilder.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser(username).password(password).authorities("USER");

    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().
                authorizeRequests().anyRequest().
                authenticated().and().httpBasic();

    }
*/
}
