package com.piseth.java.school.phoneshopenight.config.security;

import static com.piseth.java.school.phoneshopenight.config.security.PermissionEnum.BRAND_READ;
import static com.piseth.java.school.phoneshopenight.config.security.PermissionEnum.BRAND_WRITE;
import static com.piseth.java.school.phoneshopenight.config.security.PermissionEnum.MODEL_READ;
import static com.piseth.java.school.phoneshopenight.config.security.PermissionEnum.MODEL_WRITE;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {	
	
	ADMIN(Set.of(BRAND_READ,BRAND_WRITE,MODEL_READ,MODEL_WRITE)), 
	SALE(Set.of(BRAND_READ,BRAND_WRITE));
	
	private Set<PermissionEnum> permission;

}
