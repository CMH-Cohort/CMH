package org.wcci.cmh;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface UserRepository extends CrudRepository<User, Long> {

    // This method allows us to search the 'title' field of the users.
    Collection<User> findByTitleLike(String title);

    // This method allows us to do a case-insensitive search of the 'title' field of the users.
    Collection<User> findByTitleIgnoreCaseLike(String title);

    // This method allows us to do a case-insensitive selection of 'title' field of users.
    User findByTitleIgnoreCase(String title);

}