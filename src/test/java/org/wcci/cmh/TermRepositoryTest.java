package org.wcci.cmh;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TermRepositoryTest {

	@Resource
	private TermRepository underTest;
	
	@Test
	public void shouldFindAtLeastOne() {
		Iterable<Term> all = underTest.findAll();
		
		assertThat(all.iterator().hasNext(), is(true));
	}
}
