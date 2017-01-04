package org.wcci.cmh;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TermStatusRepository extends CrudRepository<TermStatus, Long> {
	
   Collection<TermStatus> findByUser (User user);

}
