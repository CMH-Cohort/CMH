package org.wcci.cmh;

import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class
public class UserRepositoryJpaTest {
	
	@Resource
	private UserRepository underTest;
	
	@Test
	public void shouldFindAtLeastOne() {
		Iterable<User> all = underTest.findAll();
		
		assertThat(all.iterator().hasNext(), is(true));
	}

}
