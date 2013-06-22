package com.skalicky.springdata.mongodb.helloworld.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author <a href="mailto:skalicky.tomas@gmail.com">Tomas Skalicky</a>
 */
@Document(collection = "users")
@ToString
@NoArgsConstructor
public class User {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public enum CollectionKey {
        ID("id"), USERNAME("username"), PASSWORD("password");

        @Getter
        private final String dbLabel;

        private CollectionKey(String dbLabel) {
            this.dbLabel = dbLabel;
        }
    }
}
