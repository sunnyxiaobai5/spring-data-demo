package com.sunnyxiaobai5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.inject.Inject;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(
            AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
//                .passwordEncoder(passwordEncoder());
//                .inMemoryAuthentication()
//                .withUser("admin").password("123456").roles("USER")
//                .and().withUser("user").password("123456").roles("USER", "ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //所有请求都需要用户认证
                .authorizeRequests()
                .antMatchers("/bower_components/**", "/signup").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                        //允许用户通过表单认证
                .formLogin()
////                .loginPage("/scripts/app/account/login/login.html")
                .permitAll();
//                .and()
//                //允许用户通过Http Basic认证
//                .httpBasic()
//                .and()
//                .logout()
//                .logoutUrl("/logout");
    }
}
