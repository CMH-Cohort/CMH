package org.wcci.cmh;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class TermControllerTest {

	@InjectMocks
	private TermController underTest;

	@Mock
	private TermRepository repository;

	@Mock
	private Collection<Term> searchResults;
	
	@Mock
	private Model model;
	
	@Captor
	private ArgumentCaptor<Term> termCaptor;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void shouldSearch() {
		when(repository.findByTitleIgnoreCaseLike("%Nonsense%")).thenReturn(searchResults);

		underTest.search("Nonsense", model);

		verify(model).addAttribute("terms",searchResults);
	}
	
	@Test
	public void shouldReturnSearchPage() {
		String result = underTest.search("NoMatter", model);
				
		assertEquals("term-list", result);
	}
	
	@Test
	public void shouldAddTerm(){
		
		underTest.add("a Created Term", model);
		
		verify(repository).save(termCaptor.capture());
		Term capturedTerm = termCaptor.getValue();
		
		assertEquals("a Created Term", capturedTerm.getTitle());
	}
	


}