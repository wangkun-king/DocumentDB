package com.wangkun.dao;

import java.util.List;

import com.wangkun.model.User;

public interface UserDao {

	public List<User> readUsers();
	
	public User createUser(User user);
	
	public User readUser(String id);
	
	public User updateUser(String id);
	
	public boolean deleteUser(String id);
	
}
