package com.ramkishore.authorizationmicroservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ramkishore.authorizationmicroservice.model.AuthRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class AuthorizationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AuthorizationController authorizationController;

    @Test
    public void contextLoads() {
        assertNotNull(authorizationController);
    }

    @Test
    public void loginTestSuccess() throws Exception {
        AuthRequest admin = new AuthRequest("ram", "1234");

        ResultActions actions = mockMvc
                .perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(asJsonString(admin)));
        actions.andExpect(status().isOk());
    }

    @Test
    public void loginTestFail() throws Exception {
        AuthRequest admin = new AuthRequest("JCANJB", "12345654HBdhvWDWW");

        ResultActions actions = mockMvc
                .perform(post("/authenticate").contentType(MediaType.APPLICATION_JSON).content(asJsonString(admin)));
        actions.andExpect(status().isNotFound());
    }

    public static String asJsonString(AuthRequest admin) {
        try {
            return new ObjectMapper().writeValueAsString(admin);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
