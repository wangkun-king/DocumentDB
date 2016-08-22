package com.wangkun.dao;

import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.DocumentClient;

public class DocumentDBClientFactory {
	private static final String HOST = "https://wangkun.documents.azure.cn:443/";
	private static final String MASTER_KEY = "OxHoP8OTXCRq1g06ZN9b1j0bZqgKiHzIGuADM4rU0EMRkznrVPghj4YN7gQrCjrojqpF5Q8G3GqmDwpm0eYuYA==";
	
	private static DocumentClient documentClient;
	
	public static DocumentClient getDocumentClient() {
		if(documentClient == null){
			documentClient = new DocumentClient(HOST, MASTER_KEY, ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
		}
		return documentClient;
	}

}
