package org.wcci.cmh;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.Collection;

import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class TermStatusControllerTest {

	@InjectMocks
    private TermStatusController underTest;

    @Mock
    private TermStatusRepository repository;

    @Mock
    private Collection<TermStatus> searchResults;

    @Mock
    private Model model;

    @Mock
    private TermStatus termstatus;
    
    @Mock
    private Term term;

    @Captor
    private ArgumentCaptor<Term> termCaptor;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

	@Test
	public void shouldMarkDone() {
		 underTest.add("a Created TermStatus", model);

	        verify(repository).save(termCaptor.capture());
	        TermStatus capturedTermStatus = termCaptor.getValue();

	        assertEquals("a Created TermStatus", capturedTermStatus.getDone());
	}
}
