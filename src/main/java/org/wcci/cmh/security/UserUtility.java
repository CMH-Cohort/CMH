package org.wcci.cmh.security;

import javax.annotation.Resource;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.wcci.cmh.User;
import org.wcci.cmh.UserRepository;

@Component
public class UserUtility {

	@Resource
	private UserRepository userRepository;
	
    public User currentUser() {
    	Authentication authentication = securityContext().getAuthentication();
    	String currentPrincipalName = authentication.getName();
        return userRepository.findByUsernameIgnoreCase(currentPrincipalName);
    }
    
	private SecurityContext securityContext() {
		return SecurityContextHolder.getContext();
	}
}
