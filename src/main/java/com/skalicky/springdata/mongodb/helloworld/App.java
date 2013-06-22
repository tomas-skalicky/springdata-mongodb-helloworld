package com.skalicky.springdata.mongodb.helloworld;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.skalicky.springdata.mongodb.helloworld.config.SpringMongoConfig;
import com.skalicky.springdata.mongodb.helloworld.domain.User;
import com.skalicky.springdata.mongodb.helloworld.service.UserService;

/**
 * NOTE: We let this class {@link App} to be a candidate for auto-detection. As a result, we can inject
 * {@link MongoTemplate} and other beans in it.
 * 
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 */
@Component
public class App {

    @Inject
    private UserService userService;

    public void run() {
        User user = new User("TomasSkalicky", "password");
        this.userService.insertOrUpdate(user);

        user.setPassword("new password");
        this.userService.updatePasswordOf(user);

        this.userService.remove(user);

        this.userService.findAll();
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
        App app = context.getBean(App.class);
        app.run();
    }
}
