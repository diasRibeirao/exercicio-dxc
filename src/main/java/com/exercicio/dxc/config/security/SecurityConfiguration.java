package com.exercicio.dxc.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@Configuration
@EnableWebSecurity
@EnableAuthorizationServer
@EnableResourceServer
public class SecurityConfiguration {
	
	private static final String[] PUBLIC_MATCHERS = { 
			"/h2-console/**", 
			"/v3/api-docs/**", 
			"/swagger-ui/**",
			"/swagger-ui.html"
		};

	@Bean
	protected WebSecurityCustomizer webSecurityCustomizer() {
	    return (web) -> web.ignoring().antMatchers(PUBLIC_MATCHERS);
	}
	
	@Bean
	protected AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.inMemoryAuthentication()
            .withUser("dxc")
            .password(passwordEncoder().encode("dxc123"))
            .roles("ADMIN");
        return authenticationManagerBuilder.build();
    }
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}