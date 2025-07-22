package com.college.yi.bookmanager.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.college.yi.bookmanager.entity.UserEntity;

@Mapper
public interface UserRepository {
	
	@Select("SELECT id,username,password,role,enabled FROM users WHERE username=#{username}")
	UserEntity findByUsername(String username);
	
	@Insert("INSERT INTO users(username,password,role,enabled) values (#{username},#{password},#{role},#{enabled})")
	void insert(UserEntity user);
	
	@Update("UPDATE users SET username=#{username},password=#{password},role=#{role},enabled=#{enabled} WHERE id=#{id}")
	void update(UserEntity user);
	
	@Delete("DELETE FROM users WHERE username=#{username}")
	void delete(String username);
	
}
