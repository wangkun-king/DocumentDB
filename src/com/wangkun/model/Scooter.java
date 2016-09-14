package com.wangkun.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Scooter {

	private Integer id;
	private String nickname;
	private String key;
}
