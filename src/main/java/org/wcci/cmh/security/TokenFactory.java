package org.wcci.cmh.security;

import static java.util.Collections.singleton;

import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class TokenFactory {

	private static final Set<SimpleGrantedAuthority> GRANTED_AUTHORITIES = singleton(
			new SimpleGrantedAuthority("user"));

	public Authentication create(String userName, String password) {
		return new UsernamePasswordAuthenticationToken(userName, password, GRANTED_AUTHORITIES);
	}
}