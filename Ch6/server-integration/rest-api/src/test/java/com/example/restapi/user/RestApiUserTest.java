package com.example.restapi.user;

import com.example.restapi.AbstractRestApiTest;
import com.example.restapi.dto.UserV1Dto;
import com.example.service.user.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Tests the user endpoint.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
public class RestApiUserTest extends AbstractRestApiTest {
    @Autowired
    protected UserService userService;

    @Test
    public void createAUserTest() throws Exception {
        final MockHttpServletResponse result = mockMvc.perform(post("/api/v1/public/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeJson(UserV1Dto.builder().withUsername("ex1@example.com")
                        .withPassword("123456a$").build())))
                .andReturn()
                .getResponse();

        assertThat(result.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        final UserV1Dto user = readJson(result.getContentAsString(), UserV1Dto.class);
        assertThat(user.getUsername()).isEqualTo("ex1@example.com");
    }

    @Test
    public void createAUserTwiceTest() throws Exception {
        final MockHttpServletResponse result = mockMvc.perform(post("/api/v1/public/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeJson(UserV1Dto.builder().withUsername("ex1@example.com")
                        .withPassword("123456a$").build())))
                .andReturn()
                .getResponse();

        assertThat(result.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        final MockHttpServletResponse result2 = mockMvc.perform(post("/api/v1/public/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeJson(UserV1Dto.builder().withUsername("ex1@example.com")
                        .withPassword("123456a$").build())))
                .andReturn()
                .getResponse();
        assertThat(result2.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
    }

    @Test
    public void getUserInTheSessionTest() throws Exception {
        final MockHttpServletResponse result = mockMvc.perform(get("/api/v1/secured/users/me")
                .header("Authorization", "bearer " + getAccessToken())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(result.getStatus()).isEqualTo(HttpStatus.OK.value());
        final UserV1Dto user = readJson(result.getContentAsString(), UserV1Dto.class);
        assertThat(user.getUsername()).isEqualTo(testUser.getUsername());
    }

    @Test
    public void refreshTest() throws Exception {
        final String accessTokenAfterRefresh = readJson(mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization", "Basic d2ViYXBwOnRlc3Q=")
                .content(String.format("grant_type=refresh_token&refresh_token=%s", getTokens().get("refresh_token"))))
                .andReturn()
                .getResponse().getContentAsString(), new TypeReference<Map<String, String>>() {
        }).get("access_token");

        final MockHttpServletResponse result = mockMvc.perform(get("/api/v1/secured/users/me")
                .header("Authorization", "bearer " + accessTokenAfterRefresh)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        assertThat(result.getStatus()).isEqualTo(HttpStatus.OK.value());
        final UserV1Dto user = readJson(result.getContentAsString(), UserV1Dto.class);
        assertThat(user.getUsername()).isEqualTo(testUser.getUsername());
    }
}
