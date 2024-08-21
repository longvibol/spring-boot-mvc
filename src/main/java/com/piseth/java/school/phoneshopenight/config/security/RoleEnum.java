package com.piseth.java.school.phoneshopenight.config.security;

import static com.piseth.java.school.phoneshopenight.config.security.PermissionEnum.BRAND_READ;
import static com.piseth.java.school.phoneshopenight.config.security.PermissionEnum.BRAND_WRITE;
import static com.piseth.java.school.phoneshopenight.config.security.PermissionEnum.MODEL_READ;
import static com.piseth.java.school.phoneshopenight.config.security.PermissionEnum.MODEL_WRITE;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {	
	
	
	// we use "Set" beacuse it now no duplicate element 
	ADMIN(Set.of(BRAND_WRITE, BRAND_READ, MODEL_WRITE, MODEL_READ)),
	SALE(Set.of(BRAND_READ, MODEL_READ));
	
	// we create Set of the Permission Enum 
	private Set<PermissionEnum> permissions;
	
	
	//We create function to get the implementation of Grandtedthority 
	public Set<SimpleGrantedAuthority> getAuthorities(){		
		// this = object it can be ADMIN or SALE
		Set<SimpleGrantedAuthority> grantedAuthorities = this.permissions.stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getDescription()))
			.collect(Collectors.toSet());
		
		// we add all the role from Enum to role 
		SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_"+this.name());
		grantedAuthorities.add(role);
		
		System.out.println(grantedAuthorities);
		
		return grantedAuthorities;
	}

}
