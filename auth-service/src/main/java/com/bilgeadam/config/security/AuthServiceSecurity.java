package com.bilgeadam.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class AuthServiceSecurity extends WebSecurityConfigurerAdapter {

    @Bean
    JwtTokenFilter getJwtTokenFilter(){
        return new JwtTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        /**
         * ATTENTION! To use swagger, we need to give access permissions that are needed.
         * By the way, we have to give access permission for the endpoint that directs to login page.
         */

        http.authorizeRequests().antMatchers(
                "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/v1/auth/dologin","/v1/auth/validatetoken")
                .permitAll().anyRequest().authenticated(); // all accesses will be denied except these endpoints.
        /**
         * We're overriding configure method to control all actions.
         * So we have to identify and manage all access requests.
         */
        http.addFilterBefore(getJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
