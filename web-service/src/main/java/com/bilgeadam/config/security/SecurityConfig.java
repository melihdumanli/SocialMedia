package com.bilgeadam.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    MVCTokenFilter mvcTokenFilter(){
        return new MVCTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * Web, csrf should be activated.
         * Post requests will be rejected.
         * Get requests will be accepted.
         */
        http.csrf().disable();
        /**
         * Permit all http requests without authorization.
         */
        // http.authorizeRequests().anyRequest().permitAll();
        /**
         * This command is based on original configuration and redirects all requests to login.
         */

        /**
         * The codes below are the standard codes.
         * formLogin() method redirects user for authorization to a web page.
         * httpBasic() method redirects user for authorization to browser's custom panel.
         */
       /*
        http.authorizeRequests((requests) -> requests.anyRequest().authenticated());
        http.formLogin();
        http.httpBasic();
*/
        http.authorizeRequests()
                /**
                 * permits user to access http://localhost/register
                 */
                .antMatchers("/register","/login").permitAll()
                /**
                 * all other requests will be authenticated.
                 */
                .anyRequest().authenticated();
        /**
         * Unless the login form will be stated, all login page activities will be provided and managed by Spring.
         */
         /*
         http.formLogin();
         */
        /**
         * The login form which provided by us will be used for authentication.
         */
        http.formLogin().loginPage("/login").loginProcessingUrl("/login");

        /**
         * We should define our own user to Spring before processed by Spring Filter or Servlet Filter.
         */
        //http.addFilterBefore(mvcTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
