package org.wcci.cmh;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TermRepository extends CrudRepository<Term, Long> {

	// This method allows us to search the 'title' field of the terms.
	Collection<Term> findByTitleLike(String title);

	// This method allows us to do a case-insensitive search of the 'title' field of the terms.
	Collection<Term> findByTitleIgnoreCaseLike(String title);

}