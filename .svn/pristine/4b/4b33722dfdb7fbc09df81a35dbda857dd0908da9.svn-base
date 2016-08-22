package test;

import org.junit.Before;
import org.junit.Test;

import com.wangkun.dao.UserDaoImpl;
import com.wangkun.model.User;

import controller.UserController;

public class UserDocumentDBTest {

	private UserController userController;
	
	@Before
	public void setup(){
		userController = new UserController(new UserDaoImpl());
	}
	@Test
	public void createUserTest(){
		User user = User.builder().name("wangkun").id("wangkun_id").pwd("mima").build();
		user= userController.createUser(user);
		System.out.println(user);
	}
}
