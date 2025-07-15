package com.college.yi.bookmanager.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.college.yi.bookmanager.entity.UserEntity;

@Mapper
public interface UserRepository {
	
	@Select("SELECT id,username,password,role,enabled FROM users WHERE username=#{username}")
	UserEntity findByUsername(String username);
	
}
