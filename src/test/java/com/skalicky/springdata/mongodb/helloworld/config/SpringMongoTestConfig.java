package com.skalicky.springdata.mongodb.helloworld.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import com.mongodb.MongoClient;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 */
@Configuration
@ComponentScan(basePackages = SpringMongoConfig.BASE_APP_PACKAGE)
public class SpringMongoTestConfig {

    static final String BASE_APP_PACKAGE = "com.skalicky.springdata.mongodb.helloworld";
    public static final String DATABASE_NAME = "helloWorld-testDb";

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(new MongoClient(), DATABASE_NAME);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}
