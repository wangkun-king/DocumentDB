package controller;

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
	

}
