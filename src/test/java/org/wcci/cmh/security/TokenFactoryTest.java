package org.wcci.cmh.security;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.Authentication;

public class TokenFactoryTest {

	private TokenFactory underTest;
	private Authentication createdToken;

	@Before
	public void setup() {
		
		underTest = new TokenFactory();
		
		createdToken = underTest.create("username", "pwd");
	}
	
	@Test
	public void shouldPutUserNameInToken() {
		
		assertThat(createdToken.getName(), is("username"));
	}

	@Test
	public void shouldPutPasswordInToken() {
		
		assertThat(createdToken.getCredentials(), is("pwd"));
	}
}
