package com.example.cookinn.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
/*
 * @secure(한명만) 활성
 * @PreAuthorize(권한 두개 이상) 어노테이션 활성화
 * */
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();

        http.authorizeRequests()
                /*.antMatchers("/cookinn/**").authenticated()
                .antMatchers("/user/**").access("hasRole('USER')")
                .antMatchers("/cookinn/store/**").access("hasRole('ADMIN')") // stores는 조회
                .antMatchers("/cookinn/item/**").access("hasRole('ADMIN')")  // items는 조회.
                .antMatchers("/admin/**").access("hasRole('ADMIN')")*/
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/cookin")
                .failureUrl("/aaa")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

        return http.build();
    }
}
