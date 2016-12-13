package org.wcci.cmh;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import junit.framework.Assert;

public class TermControllerTest {

	@InjectMocks
	private TermController underTest;

	@Mock
	private TermRepository repository;

	@Mock
	private Collection<Term> searchResults;
	
	@Mock
	private Model model;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldSearch() {
		when(repository.findByTitleLike("%Nonsense%")).thenReturn(searchResults);

		underTest.search("Nonsense", model);

		verify(model).addAttribute("terms",searchResults);
	}
	
	@Test
	public void shouldReturnSearchPage() {
		String result = underTest.search("NoMatter", model);
				
		Assert.assertEquals("term-list", result);
	}
	


}