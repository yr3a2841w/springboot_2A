package com.college.yi.bookmanager.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.college.yi.bookmanager.entity.UserEntity;
import com.college.yi.bookmanager.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private final UserRepository userRepository;
	
	public UserDetails loadUserByUsername(String username) {
		UserEntity user=userRepository.findByUsername(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("ユーザーが存在しません:"+username);
		}else {
			User.UserBuilder builder = User.withUsername(user.getUsername());
			
			builder.password("{noop}"+user.getPassword());
			builder.roles(user.getRole());
			builder.disabled(!user.isEnabled());
			
			return builder.build();
			
		}
	}

}
