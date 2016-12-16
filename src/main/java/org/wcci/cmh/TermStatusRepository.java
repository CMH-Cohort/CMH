package org.wcci.cmh;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

public interface TermStatusRepository extends CrudRepository<TermStatus, Long> {
	
    // This method allows us to search the 'done' field of the statuses.
    Collection<TermStatus> findByDoneLike(String done);

    // This method allows us to do a case-insensitive search of the 'done' field of the statuses.
    Collection<TermStatus> findByDoneIgnoreCaseLike(String done);

    // This method allows us to do a case-insensitive selection of 'done' field of statuses.
    TermStatus findByDoneIgnoreCase(String done);


}
