package com.ramkishore.authorizationmicroservice.model;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class AuthorizationModelTest {

    @Test
    public void userNoArgsTest() {
        User user=new User();
        assertNotNull(user);
    }

    @Test
    public void userAllArgsTest() {
        User user=new User(1,"Admin","admin");
        User expected=new User();
        expected.setId(user.getId());
        expected.setUsername(user.getUsername());
        expected.setPassword(user.getPassword());
        assertThat(expected).usingRecursiveComparison().isEqualTo(user);
    }
}
