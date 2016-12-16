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
public class UserRepositoryJpaTest {

    @Resource
    private UserRepository underTest;

    @Test
    public void shouldFindAtLeastOne() {
        Iterable<User> all = underTest.findAll();

        assertThat(all.iterator().hasNext(), is(true));
    }

}
