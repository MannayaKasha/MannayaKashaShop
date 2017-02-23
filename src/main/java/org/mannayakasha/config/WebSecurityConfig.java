package org.mannayakasha.config;

import org.mannayakasha.authentication.MyDBAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author Pavel
 * @version 1.0
 */

@Configuration
// @EnableWebSecurity = @EnableWebMVCSecurity + Extra features
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyDBAuthenticationService myDBAauthenticationService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // For User in database.
        auth.userDetailsService(myDBAauthenticationService);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // The pages requires login as CLIENT or MANAGER.
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/product", "/orderList").access("hasRole('ROLE_MANAGER')");

        http.authorizeRequests().antMatchers("/order", "/accountInfo").access("hasAnyRole('ROLE_CLIENT', 'ROLE_MANAGER')");

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Config for Login Form
        http.authorizeRequests().and().formLogin()
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")
                .defaultSuccessUrl("/accountInfo")
                .failureUrl("/login?error=true")
                .usernameParameter("userName")
                .passwordParameter("password")
                // Config for Logout Page
                // (Go to home page).
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");

    }
}
