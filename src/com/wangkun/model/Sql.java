package com.wangkun.model;


import com.microsoft.azure.documentdb.SqlParameter;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sql {

	private String queryText;
	private SqlParameter parameters;
	
}
