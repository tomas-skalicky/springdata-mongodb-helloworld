package com.skalicky.springdata.mongodb.helloworld.service;

import com.skalicky.springdata.mongodb.helloworld.domain.User;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 */
public interface UserService {

    void insert(User user);

    void insertOrUpdate(User user);

    void updatePasswordOf(User user);

    void remove(User user);
}
