package org.wcci.cmh;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.wcci.cmh.security.TokenFactory;

public class CmhAuthenticationProviderTest {

	private static final String PASSWORD = "password";

	private static final String USER_NAME = "userName";

	@InjectMocks
	private CmhAuthenticationProvider underTest;

	@Mock
	private UserRepository userRepository;

	@Mock
	private Authentication authentication;

	@Mock
	private User validUser;

	@Mock
	private TokenFactory tokenFactory;

	@Mock
	private Authentication token;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void shouldSupportUserNamePasswordAuthentication() {

		boolean supported = underTest.supports(UsernamePasswordAuthenticationToken.class);

		assertThat(supported, is(true));
	}

	@Test
	public void shouldNotSupportOtherTypesOfAuthentication() {
		class SomeOtherTypeOfAuthentication {
		}

		boolean supported = underTest.supports(SomeOtherTypeOfAuthentication.class);

		assertThat(supported, is(false));
	}

	@Test
	public void shouldNotAuthenticateAUserWhoseNameIsNotFound() {

		whenRetrievingUser().thenReturn(null);

		Authentication result = underTest.authenticate(authentication);

		assertThat(result, is(nullValue()));
	}

	private OngoingStubbing<User> whenRetrievingUser() {

		setupLogin();

		return when(userRepository.findByUsernameAndPassword(USER_NAME, PASSWORD));
	}

	private void setupLogin() {
		when(authentication.getName()).thenReturn(USER_NAME);
		when(authentication.getCredentials()).thenReturn(PASSWORD);
	}

	@Test
	public void shouldAuthenticateIfValidUser() {
		
		whenRetrievingUser().thenReturn(validUser);
		when(tokenFactory.create(USER_NAME, PASSWORD)).thenReturn(token);
		
		Authentication result = underTest.authenticate(authentication);

		assertThat(result, is(token));
	}
}
