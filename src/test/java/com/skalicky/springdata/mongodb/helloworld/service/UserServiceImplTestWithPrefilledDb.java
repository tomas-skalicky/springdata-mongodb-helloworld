package com.skalicky.springdata.mongodb.helloworld.service;

import static org.junit.Assert.*;
import static org.springframework.data.mongodb.core.query.Query.*;

import javax.inject.Inject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.skalicky.springdata.mongodb.helloworld.config.SpringMongoTestConfig;
import com.skalicky.springdata.mongodb.helloworld.domain.User;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMongoTestConfig.class)
public class UserServiceImplTestWithPrefilledDb {

    @Inject
    private UserService userService;
    @Inject
    private MongoTemplate mongo;

    private static final String USERNAME_1 = "TomasSkalicky";
    private static final String USERNAME_2 = "tom";

    @Before
    public void prefillCollections() {
        this.mongo.dropCollection(User.class);
        this.userService.insert(new User(USERNAME_1, "passwd"));
        this.userService.insert(new User(USERNAME_2, "passwd2"));
    }

    @After
    public void dropCollections() {
        this.mongo.dropCollection(User.class);
    }

    @Test
    public void testUpdatePasswordOf() throws Exception {
        // Prepares an input.
        Query searchQuery = query(Criteria.where(User.CollectionKey.USERNAME.getDbLabel()).is(USERNAME_1));
        User originalUser = this.mongo.findOne(searchQuery, User.class);
        String newPassword = "new password";
        originalUser.setPassword(newPassword);

        // Calls the tested method.
        this.userService.updatePasswordOf(originalUser);

        // Checks the result.
        User newUser = this.mongo.findOne(searchQuery, User.class);
        assertEquals(newPassword, newUser.getPassword());
    }

    @Test
    public void testRemove() throws Exception {
        // Prepares an input.
        Query searchQuery = query(Criteria.where(User.CollectionKey.USERNAME.getDbLabel()).is(USERNAME_1));
        User user = this.mongo.findOne(searchQuery, User.class);

        // Calls the tested method.
        this.userService.remove(user);

        // Checks the result.
        assertEquals(null, this.mongo.findOne(searchQuery, User.class));
    }
}
