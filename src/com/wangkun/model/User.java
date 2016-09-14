package com.wangkun.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {

	private String id;
	private String name;
	private String pwd;
	private Scooter scooter;
	private List<Scooter> scooterList;
}
