package org.wcci.cmh;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TermRepository extends CrudRepository<Term, Long> {

	List<Term> findById(long id);

}