package com.kuganappa.wishlist.repository;

import com.kuganappa.wishlist.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void createUser_shouldInsertIntoDatabase() {
        User user = new User("fred", "fred@gmail.com", "123", LocalDate.of(1994, 2, 1));
        userRepository.createUser(user);

        User saved = userRepository.getUserFromName("fred");

        assertEquals("fred", saved.getUserName());
    }
}
