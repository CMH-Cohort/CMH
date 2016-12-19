package org.wcci.cmh;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TermStatusRepository extends CrudRepository<TermStatus, Long> {
	
    // This method allows us to search the 'done' field of the statuses.
    Collection<TermStatus> findByDone(boolean done);


}
