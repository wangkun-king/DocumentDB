package com.wangkun.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;
import com.microsoft.azure.documentdb.SqlParameter;
import com.microsoft.azure.documentdb.SqlParameterCollection;
import com.microsoft.azure.documentdb.SqlQuerySpec;
import com.wangkun.model.User;

public class UserDaoImpl implements UserDao {

	private static final String DATABASE_ID = "User";
	private static final String COLLECTION_ID = "UserCollection";

	private static DocumentClient documentClient = DocumentDBClientFactory.getDocumentClient();

	private static Gson gson = new Gson();

	private static DocumentCollection collectionCache;

	private static Database databaseCache;

	@Override
	public List<User> readUsers() {
		List<User> userList = new ArrayList<User>();

		List<Document> documentList = documentClient.queryDocuments(getUserCollection().getSelfLink(), "select * from root r where r.entityType='User'", null)
				.getQueryIterable().toList();

		for (Document document : documentList) {
			userList.add(gson.fromJson(document.toString(), User.class));
		}
		return userList;
	}

	@Override
	public User createUser(User user) {
		Document userDocument = new Document(gson.toJson(user));
		userDocument.set("entityType", "User");
		try {
			userDocument = documentClient.createDocument(getUserCollection().getSelfLink(), userDocument, null, false).getResource();
		} catch (DocumentClientException e) {
			e.printStackTrace();
		}
		return gson.fromJson(userDocument.toString(), User.class);
	}

	private DocumentCollection getUserCollection() {
		if (collectionCache == null) {
			List<DocumentCollection> collectionList = documentClient
					.queryCollections(getTodoDatabase().getSelfLink(), "SELECT * FROM root r WHERE r.id='" + COLLECTION_ID + "'", null).getQueryIterable()
					.toList();
			if (collectionList.size() > 0) {
				collectionCache = collectionList.get(0);
			} else {
				DocumentCollection dc = new DocumentCollection();
				dc.setId(COLLECTION_ID);
				try {
					collectionCache = documentClient.createCollection(getTodoDatabase().getSelfLink(), dc, null).getResource();
				} catch (DocumentClientException e) {
					e.printStackTrace();
				}
			}
		}
		return collectionCache;
	}

	private Database getTodoDatabase() {
		if (databaseCache == null) {
			List<Database> dataBaseList = documentClient.queryDatabases("SELECT * FROM root r Where r.id='" + DATABASE_ID + "'", null).getQueryIterable()
					.toList();
			if (dataBaseList.size() > 0) {
				databaseCache = dataBaseList.get(0);
			} else {
				Database db = new Database();
				db.setId(DATABASE_ID);
				try {
					databaseCache = documentClient.createDatabase(db, null).getResource();
				} catch (DocumentClientException e) {
					e.printStackTrace();
				}
			}

		}
		return databaseCache;
	}

	@Override
	public User readUser(String id) {
		// List<Document> documentList =
		// documentClient.queryDocuments(getUserCollection().getSelfLink(),
		// "select * from root r where r.id='"+id+"'",
		// null).getQueryIterable().toList();
		// if(documentList.size()>0){
		// user = gson.fromJson(documentList.get(0).toString(), User.class);
		// }
		Document userDocument = getUserDocumentById(id);
		if(userDocument==null){
			return null;
		}
		User user = gson.fromJson(userDocument.toString(), User.class);
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		boolean flag = false;
		if (StringUtils.isNotEmpty(user.getId())) {
			Document doc = getUserDocumentById(user.getId());
			if (doc == null) {
				return flag;
			}
			// User doc2user = gson.fromJson(doc.toString(), User.class);
			if (StringUtils.isNotEmpty(user.getName())) {
				// doc2user.setName(user.getName());
				doc.set("name", user.getName());
			}
			if (user.getScooter() != null) {
				// doc2user.setScooter(user.getScooter());
				doc.set("scooter", user.getScooter());
			}
			doc.set("scooterList", user.getScooterList());
			// doc = new Document(gson.toJson(doc2user));
			try {
				Document doc2 = documentClient.replaceDocument(doc, null).getResource();
				if (doc2 != null) {
					flag = true;
					System.out.println(doc2);
				}
			} catch (DocumentClientException e) {
				e.printStackTrace();
				return false;
			}

		}

		return flag;
	}

	@Override
	public boolean deleteUser(String id) {
		boolean flag = false;
		Document doc = getUserDocumentById(id);
		if (doc == null) {
			return false;
		}
		try {
			System.out.println(documentClient.deleteDocument(doc.getSelfLink(), null).getResponseHeaders());
			flag = true;
		} catch (DocumentClientException e) {
			e.printStackTrace();
			return false;
		}

		return flag;
	}

	private Document getUserDocumentById(String id) {
		// new SqlQuerySpec("select * from root r where r.id=@id", new
		// SqlParameterCollection(new SqlParameter("@id",id)));
		List<Document> documentList = documentClient
				.queryDocuments(getUserCollection().getSelfLink(),
						new SqlQuerySpec("select * from root r where r.id=@id", new SqlParameterCollection(new SqlParameter("@id", id))), null)
				.getQueryIterable().toList();
		if (documentList.size() > 0) {
			return documentList.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<Document> customQuery(SqlQuerySpec sqlQuerySpec) {
		List<Document> documentList = documentClient.queryDocuments(getUserCollection().getSelfLink(), sqlQuerySpec, null)
				.getQueryIterable().toList();
		return documentList;
	}

	@Override
	public List<Document> customQuery(String sql) {
		
		return null;
	}
	
	
}
