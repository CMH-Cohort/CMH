package org.wcci.cmh;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.Collections;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
@RunWith(SpringRunner.class)
public class TermControllerMvcTest {

	@Resource
	private MockMvc mockMvcSupport;

	// this replaces the real bean in the Spring context with a Mockito mock
	@MockBean
	private TermRepository repository;

	@Mock
	// this is not managed by Spring so we just use a standard @Mock annotation
	private Collection<Term> results;

	/**
	 * <p>These methods used below are imported via static imports:</p>
	 * 
	 * <ul>
	 * <li>{@link MockMvcRequestBuilders#get(String, Object...)}: performs an HTTP
	 * GET for the specified URL (like navigating to it in your browser)</li>
	 * <li>{@link MockMvcResultMatchers#status}(): gives the <a href="https://en.wikipedia.org/wiki/List_of_HTTP_status_codes">HTTP response status</a>
	 * </ul>
	 * @throws Exception
	 *             because some of the mock MVC methods throw exceptions
	 */
	@Test
	public void shouldSearch() throws Exception {
		
		String searchTerm = "searchTerm";
		// I'm just returning an empty list since we don't care about the actual results
		when(repository.findByTitleLike("%" + searchTerm + "%")).thenReturn(Collections.emptyList());

		//this is doing a get request with the URL /search?searchTitle=searchTerm
		mockMvcSupport.perform(get("/search").param("searchTitle", searchTerm)).andExpect(status().isOk());
	}
}
