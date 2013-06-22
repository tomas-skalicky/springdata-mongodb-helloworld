package com.skalicky.springdata.mongodb.helloworld.service;

import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import com.skalicky.springdata.mongodb.helloworld.domain.User;

/**
 * Implementations of methods of this interface are created automatically by Spring Data based on patterns
 * which the signatures of the methods match.
 * 
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 */
public interface UserRepository extends CrudRepository<User, ObjectId> {

    /**
     * The signature of this method matches a <b>... findBy{field_name}(...) pattern</b>
     * <p>
     * At the runtime when the <i>userRepository</i> bean is being created, the {@link User} class is checked
     * whether it contains a field with such a name. If no, it throws a
     * {@link org.springframework.data.mapping.PropertyReferenceException PropertyReferenceException}.
     * <p>
     * If the returned type does not agree, a {@link java.lang.ClassCastException ClassCastException} is
     * thrown.
     */
    User findByUsername(String username);
}
