package com.wangkun.dao;

import java.util.List;

import com.google.gson.Gson;
import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createUser(User user) {
		Document userDocument = new Document(gson.toJson(user));
		userDocument.set("entityType", "User");
		try {
			userDocument = documentClient.createDocument(getUserCollection().getSelfLink(), userDocument, null, false).getResource();
		} catch (DocumentClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gson.fromJson(userDocument.toString(), User.class);
	}

	private DocumentCollection getUserCollection() {
		if(collectionCache == null){
			List<DocumentCollection> collectionList = documentClient.queryCollections(getTodoDatabase().getSelfLink(), "SELECT * FROM root r WHERE r.id='"+COLLECTION_ID+"'", null).getQueryIterable().toList();
			if(collectionList.size()>0){
				collectionCache = collectionList.get(0);
			}else {
				DocumentCollection dc = new DocumentCollection();
				dc.setId(COLLECTION_ID);
				try {
					collectionCache = documentClient.createCollection(getTodoDatabase().getSelfLink(), dc, null).getResource();
				} catch (DocumentClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return collectionCache;
	}
	private Database getTodoDatabase() {
		if(databaseCache == null){
			List<Database> dataBaseList = documentClient.queryDatabases("SELECT * FROM root r Where r.id='"+DATABASE_ID+"'", null).getQueryIterable().toList();
			if(dataBaseList.size()>0){
				databaseCache = dataBaseList.get(0);
			}else{
				Database db = new Database();
				db.setId(DATABASE_ID);
				try {
					databaseCache = documentClient.createDatabase(db, null).getResource();
				} catch (DocumentClientException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		return databaseCache;
	}
	@Override
	public User readUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
