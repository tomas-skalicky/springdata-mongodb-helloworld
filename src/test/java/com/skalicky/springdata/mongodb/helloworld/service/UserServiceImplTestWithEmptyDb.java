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
import org.springframework.util.StringUtils;

import com.skalicky.springdata.mongodb.helloworld.config.SpringMongoTestConfig;
import com.skalicky.springdata.mongodb.helloworld.domain.User;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMongoTestConfig.class)
public class UserServiceImplTestWithEmptyDb {

    @Inject
    private UserService userService;
    @Inject
    private MongoTemplate mongo;

    @Before
    @After
    public void dropCollections() {
        this.mongo.dropCollection(User.class);
    }

    @Test
    public void testInsert() throws Exception {
        // Prepares an input.
        String username = "TomasSkalicky";
        String password = "passwd";
        User user = new User(username, password);

        // Calls the tested method.
        this.userService.insert(user);

        // Checks the result.
        Query searchQuery = query(Criteria.where(User.CollectionKey.USERNAME.getDbLabel()).is(username));
        User storedUser = this.mongo.findOne(searchQuery, User.class);
        assertFalse(StringUtils.isEmpty(storedUser.getId()));
        assertEquals(password, storedUser.getPassword());
    }

    @Test
    public void testInsertOrUpdate() throws Exception {
        // Prepares an input.
        String username = "TomasSkalicky";
        String password = "passwd";
        User user = new User(username, password);

        // Calls the tested method.
        this.userService.insertOrUpdate(user);

        // Checks the result.
        Query searchQuery = query(Criteria.where(User.CollectionKey.USERNAME.getDbLabel()).is(username));
        User storedUser = this.mongo.findOne(searchQuery, User.class);
        assertFalse(StringUtils.isEmpty(storedUser.getId()));
        assertEquals(password, storedUser.getPassword());
    }
}
