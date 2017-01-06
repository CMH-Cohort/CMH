package org.wcci.cmh;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.ui.Model;

import java.util.Collection;

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
    private Collection<Term> searchResults;

    @Mock
    private Model model;

    @Mock
    private Term term;

    @Captor
    private ArgumentCaptor<Term> termCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldSearch() {
        when(termRepositoryToGetRidOf.findByTitleIgnoreCaseLike("%Nonsense%")).thenReturn(searchResults);

        underTest.search("Nonsense", model);

        verify(model).addAttribute("terms", searchResults);
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