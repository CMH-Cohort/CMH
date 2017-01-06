package org.wcci.cmh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerMvcTest {

    @Resource
    private MockMvc mockMvcSupport;

    // this replaces the real bean in the Spring context with a Mockito mock
    @MockBean
    private UserRepository repository;

//	@Mock
//	// this is not managed by Spring so we just use a standard @Mock annotation
//	private Collection<Term> results;

    /**
     * <p>These methods used below are imported via static imports:</p>
     * <p>
     * <ul>
     * <li>{@link MockMvcRequestBuilders#get(String, Object...)}: performs an HTTP
     * GET for the specified URL (like navigating to it in your browser)</li>
     * <li>{@link MockMvcResultMatchers#status}(): gives the <a href="https://en.wikipedia.org/wiki/List_of_HTTP_status_codes">HTTP response status</a>
     * </ul>
     *
     * @throws Exception because some of the mock MVC methods throw exceptions
     */
    @Test
    @WithMockUser
    public void shouldSearch() throws Exception {

        String searchTerm = "searchTerm";
        // I'm just returning an empty list since we don't care about the actual results
        when(repository.findByUsernameLike("%" + searchTerm + "%")).thenReturn(Collections.emptyList());

        //this is doing a get request with the URL /search?searchTitle=searchTerm
        mockMvcSupport.perform(get("/searchUser").param("username", searchTerm)).andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void shouldAddUser() throws Exception {

        mockMvcSupport.perform(get("/addUser").param("username", "addUser")).andExpect(status().isOk());

    }

    @Test
    @WithMockUser
    public void shouldRemoveUser() throws Exception {

        mockMvcSupport.perform(get("/removeUser").param("username", "addUser")).andExpect(status().isOk());

    }
}
