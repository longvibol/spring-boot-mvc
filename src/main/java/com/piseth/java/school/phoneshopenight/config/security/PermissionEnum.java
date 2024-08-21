package com.piseth.java.school.phoneshopenight.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum PermissionEnum {
	
	//band and model 
	
	BRAND_WRITE("brand:write"),
	BRAND_READ("brand:read"),
	MODEL_WRITE("model:write"),
	MODEL_READ("model:read");
	
	
	private String description;
	
	
	/*
	// create constractor 
	private PermissionEnum(String desc) {
		this.description = desc;
	}
	
	*/
	

}
