package controller;

import java.util.List;

import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.SqlQuerySpec;
import com.wangkun.dao.UserDao;
import com.wangkun.model.User;

public class UserController {
	private final UserDao userDao;
	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}
	public User createUser(User user){
		return userDao.createUser(user);
	}
	public List<User> readUsers(){
		return userDao.readUsers();
	}
	public User readUser(String id){
		return userDao.readUser(id);
	}
	public boolean updateUser(User user){
		return userDao.updateUser(user);
	}
	public boolean deleteUser(String id){
		return userDao.deleteUser(id);
	}
	
	public List<Document> customQuery(SqlQuerySpec sqlQuerySpec){
		return userDao.customQuery(sqlQuerySpec);
	}
	public List<Document> customQuery(String sql){
		return userDao.customQuery(sql);
	}
	

}
