package org.wcci.cmh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class TermRepositoryJpaTest {

    @Resource
    private TermRepository underTest;

    @Test
    public void shouldFindAtLeastOne() {
        Iterable<Term> all = underTest.findAll();

        assertThat(all.iterator().hasNext(), is(true));
    }
}
