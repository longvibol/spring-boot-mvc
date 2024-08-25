package com.piseth.java.school.phoneshopenight.service.impl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.security.RolesAllowed;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.piseth.java.school.phoneshopenight.config.security.AuthUser;
import com.piseth.java.school.phoneshopenight.config.security.UserService;
import com.piseth.java.school.phoneshopenight.entity.Role;
import com.piseth.java.school.phoneshopenight.entity.User;
import com.piseth.java.school.phoneshopenight.exception.ApiException;
import com.piseth.java.school.phoneshopenight.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final UserRepository repository;

	@Override
	public Optional<AuthUser> findUserByUsername(String username) {
	User user = repository.findByUsername(username)
			.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));
	
	AuthUser authUser = AuthUser.builder()
			.username(user.getUsername())
			.password(user.getPassword())
			.authorities(getAuthorities(user.getRoles()))
			.accountNonExpired(user.isAccountNonExpired())
			.accountNonLocked(user.isAccountNonLocked())
			.credentialsNonExpired(user.isCredentialsNonExpired())
			.enabled(user.isEnabled())
			.build();
		
		return Optional.ofNullable(authUser);
	}
	
	private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles){
		
		Set<SimpleGrantedAuthority> autherity1 = roles.stream()
				.map(role -> new SimpleGrantedAuthority("ROLE_"+role.getName()))
				.collect(Collectors.toSet());
		
		// from role we want to map it to SimplegrantedAuthority 
		
		Set<SimpleGrantedAuthority> autheritys = roles.stream()
				.flatMap(role ->{			
			return toStreamPermission(role);
					}).collect(Collectors.toSet());
	
	autheritys.addAll(autherity1);
	
	return autheritys;
		
	}	

	// we create separate function to convert from Role -> permission to SimpleGrantedAuthority (Need to create ROLE_ and role from user
	private Stream<SimpleGrantedAuthority> toStreamPermission(Role role){		
		// inside role it have set<permission> so we map one more step to get the permission from role 		
		return role.getPermissions().stream()
				.map(permission -> new SimpleGrantedAuthority(permission.getName()));
	}

}


























