package com.skalicky.springdata.mongodb.helloworld.service;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.skalicky.springdata.mongodb.helloworld.domain.User;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger log = Logger.getLogger(UserServiceImpl.class);

    @Inject
    private MongoTemplate mongo;

    @Override
    public void insert(User user) {
        this.log.debug("insert - before: " + user);
        this.mongo.insert(user);
        this.log.debug("insert - after: " + user);
    }

    @Override
    public void insertOrUpdate(User user) {
        this.log.debug("insertOrUpdate - before: " + user);
        this.mongo.save(user);
        this.log.debug("insertOrUpdate - after: " + user);
    }

    @Override
    public void updatePasswordOf(User user) {
        this.log.debug("updatePasswordOf: " + user);
        Query searchQuery = new Query(Criteria.where(User.CollectionKey.ID.getDbLabel()).is(user.getId()));
        Update update = Update.update(User.CollectionKey.PASSWORD.getDbLabel(), user.getPassword());
        this.mongo.updateFirst(searchQuery, update, User.class);
    }

    @Override
    public void remove(User user) {
        this.log.debug("remove: " + user);
        this.mongo.remove(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = this.mongo.findAll(User.class);
        this.log.debug("findAll: " + Arrays.toString(users.toArray()));
        return users;
    }
}
