package com.wangkun.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Parameters {

	private String name;
	private String value;
}
