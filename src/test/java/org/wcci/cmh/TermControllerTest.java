package org.wcci.cmh;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.ui.Model;
import org.wcci.cmh.security.UserUtility;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TermControllerTest {

	@InjectMocks
	private TermController underTest;

	@Mock
	private TermRepository termRepositoryToGetRidOf;

	@Mock
	private UserRepository userRepository;

	@Mock
	private User user;

	@Mock
	private UserUtility userUtility;

	@Mock
	private Model model;

	@Mock
	private Term term;
	@Mock
	private Term nonMatchingTerm;

	@Mock
	private TermStatus matchingTermStatus;
	@Mock
	private TermStatus nonMatchingTermStatus;

	@Captor
	private ArgumentCaptor<Term> termCaptor;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		when(userUtility.currentUser()).thenReturn(user);
	}

	@Test
	public void shouldSearch() {

		// associate 'term' with matchingTermStatus
		when(matchingTermStatus.getTerm()).thenReturn(term);

		String searchTerm = "Nonsense";

		// set up our search to return the matching 'term' and a non-matching
		// term
		Collection<Term> searchResults = asList(term, nonMatchingTerm);
		when(termRepositoryToGetRidOf.findByTitleIgnoreCaseLike("%" + searchTerm + "%")).thenReturn(searchResults);

		// set up our user to return two statuses: one matches 'term', one
		// doesn't
		when(user.getTermStatuses()).thenReturn(asList(matchingTermStatus, nonMatchingTermStatus));

		underTest.search(searchTerm, model);

		verify(model).addAttribute("termStatuses", singletonList(matchingTermStatus));
	}

	@Test
	public void shouldReturnSearchPage() {
		String result = underTest.search("NoMatter", model);

		assertEquals("term-list", result);
	}

	@Test
	public void shouldAddTerm() {

		underTest.add("a Created Term", model);

		verify(termRepositoryToGetRidOf).save(termCaptor.capture());
		Term capturedTerm = termCaptor.getValue();

		assertEquals("a Created Term", capturedTerm.getTitle());
	}

	@Test
	public void shouldRemoveTerm() {
		when(termRepositoryToGetRidOf.findByTitleIgnoreCase("delete term")).thenReturn(term);

		underTest.remove("delete term", model);

		verify(termRepositoryToGetRidOf).delete(term);

	}

	@Test
	public void shouldPreventDuplicates() {
		when(termRepositoryToGetRidOf.findByTitleIgnoreCase("a Created Term")).thenReturn(term);

		underTest.add("a Created Term", model);

		verify(termRepositoryToGetRidOf, never()).save(any(Term.class));
	}

}