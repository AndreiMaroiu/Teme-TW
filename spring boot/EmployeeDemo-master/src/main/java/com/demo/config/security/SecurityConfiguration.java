package com.demo.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] STATICCONTENT = {ContextPath.BOWER.getContextString(), ContextPath.BUILD.getContextString(), ContextPath.CUSTOM.getContextString(), ContextPath.DIST.getContextString(), ContextPath.PLUGINS.getContextString(), ContextPath.FAVICON.getContextString()};

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

                .authorizeRequests()
                .antMatchers(STATICCONTENT).permitAll()
                .antMatchers("/index").hasAuthority("INDEX_VIEW")
                .antMatchers("/userAccountOverview").hasAuthority("USERACCOUNT_VIEW")
                .antMatchers("/employeeOverview").hasAuthority("EMPLOYEE_VIEW")
                .antMatchers("/createEmployee").hasAuthority("EMPLOYEE_CREATE")
                .antMatchers("/editEmployee").hasAuthority("EMPLOYEE_EDIT")
                .antMatchers("/deleteEmployee").hasAuthority("EMPLOYEE_DELETE")
                .antMatchers("/departmentOverview").hasAuthority("DEPARTMENT_VIEW")
                .antMatchers("/createDepartment").hasAuthority("DEPARTMENT_CREATE")
                .antMatchers("/editDepartment").hasAuthority("DEPARTMENT_EDIT")
                .antMatchers("/deleteDepartment").hasAuthority("DEPARTMENT_DELETE")
                .antMatchers("/profile").hasAuthority("VIEW_PROFILE")
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()

                .and().exceptionHandling().accessDeniedPage("/accessDenied")
        ;
        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new PasswordEnconder();
    }
}

