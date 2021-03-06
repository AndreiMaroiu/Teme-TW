package com.tw.app.config;

import com.tw.app.handlers.AuthSuccessHandler;
import com.tw.app.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Bean
    public UserDetailsService userDetailsService()
    {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean AuthSuccessHandler authSuccessHandler()
    {
        return new AuthSuccessHandler();
    }

    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/home").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/signUp").permitAll()
                .antMatchers("/submit").permitAll()
                .antMatchers("/login**").permitAll()
//                .antMatchers("/producer").hasAnyAuthority("PRODUCER", "ADMIN")
//                .antMatchers("/trader", "/trader/**").hasAnyAuthority("TRADER", "ADMIN")
//                .antMatchers("/buyer", "/buyer/**", "/buyer**").hasAnyAuthority("BUYER", "ADMIN")
//                .antMatchers("/producer/**").hasAnyAuthority("PRODUCER", "ADMIN")
                .antMatchers("/send-message").permitAll()
                .antMatchers("/send-private-message/**").permitAll()
                .antMatchers("/send-private-message**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/home", true)
                // TODO: if redirect based on role is needed, uncomment this line
                //.successHandler(authSuccessHandler())
                .failureUrl("/login?error=true")
                .passwordParameter("password")
                .usernameParameter("username")
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
    }
}

