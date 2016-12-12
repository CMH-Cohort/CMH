package org.wcci.cmh;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TermRepository extends CrudRepository<Term, Long> {

	// This method allows us to search the 'title' field of the terms.
	Collection<Term> findByTitleLike(String title);

}