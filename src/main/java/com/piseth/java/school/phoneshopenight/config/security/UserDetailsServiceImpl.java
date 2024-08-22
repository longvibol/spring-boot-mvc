package com.piseth.java.school.phoneshopenight.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// bos username jol -> return UserDetails
		return userService.findUserByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not Found!"));
		
		// class AuthUser implements UserDetails so we can return it "UserDetails"

	}

}
