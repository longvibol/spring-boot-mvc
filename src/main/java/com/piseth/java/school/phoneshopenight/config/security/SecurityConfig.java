package com.piseth.java.school.phoneshopenight.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@SuppressWarnings("deprecation")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
				.antMatchers("/", "index.html", "css/**", "js/**").permitAll()
				.antMatchers("/brands").hasRole("SALE")
				.anyRequest()				
				.authenticated()
				.and()
				.httpBasic();
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {

		// concept Inheritance OOP
		// style implentation
		//User user1 = new User("dara", passwordEncoder.encode("dara123"), Collections.emptyList());
		
		UserDetails user1 = User.builder()
				.username("dara")
				.password(passwordEncoder.encode("dara123"))
				.roles("SALE") // inside roles they write : Assert.isTrue(!role.startsWith("ROLE_"),
				.build();

		// style interface
		UserDetails user2 = User.builder()
				.username("thida")
				.password(passwordEncoder.encode("thida123"))
				.roles("ADMIN")
				.build();

		UserDetailsService userDetailsService = new InMemoryUserDetailsManager(user1, user2);
		return userDetailsService;

	}

}
