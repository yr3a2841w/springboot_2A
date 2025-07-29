package com.college.yi.bookmanager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.college.yi.bookmanager.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	private final CustomUserDetailsService userDetailService;
	
	@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	AuthenticationManager authManager(HttpSecurity http) throws Exception{
		AuthenticationManagerBuilder authBuilder=http.getSharedObject(AuthenticationManagerBuilder.class);
		authBuilder.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
		
		return authBuilder.build();
	}

	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
		http.authorizeHttpRequests(auth -> auth
				.requestMatchers(HttpMethod.GET,"/api/books/**").hasAnyRole("ADMIN","USER")
				.requestMatchers(HttpMethod.POST,"/api/books/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT,"/api/books/**").hasRole("ADMIN")
				.requestMatchers(HttpMethod.DELETE,"/api/books/**").hasRole("ADMIN")
				.anyRequest().authenticated()
			)
			.formLogin(Customizer.withDefaults()) 
			.csrf(Customizer.withDefaults());
			
		
		return http.build();
	}
	
}
