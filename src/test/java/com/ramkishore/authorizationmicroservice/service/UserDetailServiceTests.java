package com.ramkishore.authorizationmicroservice.service;

import com.ramkishore.authorizationmicroservice.model.User;
import com.ramkishore.authorizationmicroservice.repository.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserDetailServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailService userDetailService;


    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    public void loadUserByUserNameShouldUserNameTest() {
        when(userRepository.findByUsername("ram")).
                thenReturn(new User(2,"ram","1234"));

        assertThat(userDetailService.loadUserByUsername("ram")).isNotNull();
        verify(userRepository, Mockito.times(1)).findByUsername("ram");
    }

}
