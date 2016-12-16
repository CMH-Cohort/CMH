package org.wcci.cmh;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import javax.annotation.Resource;
import javax.persistence.Entity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * I have deviated here from our previous *JpaTests, naming it after the
 * {@link Entity} rather than the repository. Seems to make more sense.
 * 
 * @author brian
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public class TermStatusJpaTest {

	@Resource
	private TermStatusRepository repository;

	/**
	 * This method assumes you've created inserts for term statuses already in import.sql.
	 */
	@Test
	public void shouldHaveTerm() {
		Iterable<TermStatus> statuses = repository.findAll();
		TermStatus firstStatus = statuses.iterator().next();
		
		assertThat(firstStatus.getTerm(), is(not(nullValue())));
	}
}