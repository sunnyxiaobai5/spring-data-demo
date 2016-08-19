package com.sunnyxiaobai5.config;

import com.sunnyxiaobai5.web.filter.CsrfCookieGeneratorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;

import javax.inject.Inject;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Inject
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //禁用csrf
                //.csrf().disable()
                //启用csrf的情况下，需要往浏览器写入cookie配合AngularJS
                .addFilterAfter(new CsrfCookieGeneratorFilter(), CsrfFilter.class)
//                .exceptionHandling()
//                .authenticationEntryPoint(authenticationEntryPoint)
                //所有请求都需要用户认证
                .authorizeRequests()
                .antMatchers("/bower_components/**", "/signup").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                //允许用户通过表单认证
                .formLogin()
//                .loginPage("/scripts/app/account/login/login.html"  )
                .permitAll();
//                .and()
//                .logout()
//                .logoutUrl("/logout");
    }
}
