package org.wcci.cmh;

import javax.annotation.Resource;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.wcci.cmh.security.TokenFactory;

@Component
public class CmhAuthenticationProvider implements AuthenticationProvider {

	@Resource
	private UserRepository userRepository;
	
	private TokenFactory tokenFactory = new TokenFactory();

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		User user = userRepository.findByUsernameAndPassword(name, password);

		if (user == null) {
			return null;
		}

		return tokenFactory.create(name, password);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
