package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.SqlParameter;
import com.microsoft.azure.documentdb.SqlParameterCollection;
import com.microsoft.azure.documentdb.SqlQuerySpec;
import com.wangkun.dao.UserDaoImpl;
import com.wangkun.model.Scooter;
import com.wangkun.model.Scooter.ScooterBuilder;
import com.wangkun.model.User;
import com.wangkun.model.User.UserBuilder;

import controller.UserController;

/**
 * 测试
 */
public class UserDocumentDBTest {

	private UserController userController;

	@Before
	public void setup() {
		userController = new UserController(new UserDaoImpl());
	}

	@Test
	public void createUserTest() {
//		User user = User.builder().name("scooter").id("scooter_id").pwd("pwd")
//				.scooter(Scooter.builder().nickname("小马达").key("钥匙").build())
//				.build();
		UserBuilder userbuilder = User.builder();
		userbuilder.id("2");
		userbuilder.name("wangkun");
		userbuilder.pwd("password");
		ScooterBuilder sb =Scooter.builder();
		sb.id(0);
		sb.nickname("小西天");
		sb.key("123456");
		Scooter scooter = sb.build();
		
		ScooterBuilder sb1 =Scooter.builder();
		sb1.id(1);
		sb1.nickname("小西天1");
		sb1.key("123456");
		Scooter scooter1 = sb1.build();
		
		ScooterBuilder sb2 =Scooter.builder();
		sb2.id(2);
		sb2.nickname("小西天2");
		sb2.key("123456");
		Scooter scooter2 = sb2.build();
		
		ScooterBuilder sb3 =Scooter.builder();
		sb3.id(3);
		sb3.nickname("小西天3");
		sb3.key("123456");
		Scooter scooter3 = sb3.build();
		
		ScooterBuilder sb4 =Scooter.builder();
		sb4.id(4);
		sb4.nickname("小西天4");
		sb4.key("123456");
		Scooter scooter4 = sb4.build();
		
		ScooterBuilder sb5 =Scooter.builder();
		sb5.id(5);
		sb5.nickname("小西天5");
		sb5.key("123456");
		Scooter scooter5 = sb5.build();
		
		List<Scooter> scooterList = new ArrayList<Scooter>();
		scooterList.add(scooter1);
		scooterList.add(scooter2);
		scooterList.add(scooter3);
		scooterList.add(scooter4);
		scooterList.add(scooter5);
		
		userbuilder.scooter(scooter);
		userbuilder.scooterList(scooterList);
		User user = userbuilder.build();
		
		user = userController.createUser(user);
		System.out.println(user);
	}
	
	@Test
	public void readUsersTest(){
		
		List<User> list = userController.readUsers();
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void readUserTest(){
		String id ="1";
		User user = userController.readUser(id);
		System.out.println(user);
	}
	
	@Test
	public void updateUserTest()
	{
		List<Scooter> scooterList = new ArrayList<Scooter>();
		Scooter scooter = Scooter.builder().id(6).nickname("小黄蜂").key("12345678").build();
		scooterList.add(scooter);
		User user = User.builder().name("kotei").id("3").scooter(scooter).scooterList(scooterList).build();
		System.out.println(userController.updateUser(user));
	}
	
	@Test
	public void deleteUserTest(){
		System.out.println(userController.deleteUser("wangkun_id"));
	}
	
	@Test
	public void customQueryTest(){
//		Sql.SqlBuilder sql = Sql.builder();
//		
//		sql.queryText("select * from roor r where r.id = @id");
//		SqlParameter sqlParameter = new SqlParameter("@id","1");
//		sql.sqlParameter(sqlParameter);
//		Map<String,Object> paramMap = new HashMap<String, Object>();
//		paramMap.put("@id", "1");
//		sql.paramMap(paramMap);
		
//		Gson gson =  new GsonBuilder().disableHtmlEscaping().create();  
//		String customSql =gson.toJson(sql.build());
//		System.out.println(customSql);
		String queryText = "select * from roor r where r.id = @id";
		
		SqlParameter sqlParameter = new SqlParameter("@id","2");
		SqlParameterCollection sqlParameterCollection =new SqlParameterCollection(sqlParameter);
		
		SqlQuerySpec sqlQuerySpec = new SqlQuerySpec(queryText, sqlParameterCollection);
		List<Document> docList = userController.customQuery(sqlQuerySpec);
		for (Document document : docList) {
			System.out.println(document);
		}
	}
}
