package com.wangkun.dao;

import java.util.List;

import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.SqlQuerySpec;
import com.wangkun.model.User;

public interface UserDao {

	public List<User> readUsers();
	
	public User createUser(User user);
	
	public User readUser(String id);
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(String id);
	
	public List<Document> customQuery(SqlQuerySpec sqlQuerySpec);
	
	public List<Document> customQuery(String sql);
}
