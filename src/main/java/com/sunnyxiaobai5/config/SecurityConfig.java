package com.sunnyxiaobai5.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(
            AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("123456").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //所有请求都需要用户认证
                .authorizeRequests()
                .anyRequest()
                .authenticated()
            .and()
                //运行用户通过表单认证
                .formLogin()
//                .loginPage("/scripts/app/account/login/login.html")
//                .permitAll()
            .and()
                //允许用户通过Http Basic认证
                .httpBasic()
            .and()
                .logout()
                .logoutUrl("/logout");
    }
}
