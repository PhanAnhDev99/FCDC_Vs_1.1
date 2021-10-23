//package com.fpt.myweb.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.AuthenticationEntryPoint;
//
//@Configuration
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private AuthenticationEntryPoint authEntryPoint;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//
//        // Tất cả các request gửi tới Web Server yêu cầu phải được xác thực
//        // (authenticated).
//        http.authorizeRequests().anyRequest().authenticated();
//
//        // Sử dụng AuthenticationEntryPoint để xác thực user/password
//        http.httpBasic().authenticationEntryPoint(authEntryPoint);
//    }
//}
