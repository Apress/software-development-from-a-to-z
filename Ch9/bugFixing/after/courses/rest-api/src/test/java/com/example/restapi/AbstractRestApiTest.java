package com.example.restapi;

import com.example.commons.builder.JsonUtils;
import com.example.persistence.entity.user.User;
import com.example.restapi.config.BaseConfig;
import com.example.service.user.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Base test others must extend so they have
 * testing capabilities.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Configuration
@ContextConfiguration(
        classes = {
                BaseConfig.class
        },
        initializers = {RestApiServerTestInitializer.class})
@WebAppConfiguration
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
@Transactional
@Rollback
@TestPropertySource("classpath:test.properties")
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractRestApiTest {
    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    protected FilterChainProxy springSecurityFilterChain;
    @Autowired
    protected UserService userService;

    protected MockMvc mockMvc;
    protected User testUser;
    protected final String testUserPassword = "123456a$";

    @Before
    public void init() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .addFilters(this.springSecurityFilterChain)
                .build();

        this.testUser = userService.create(User.builder().withUsername("testsubject@example.com").withPassword(testUserPassword).build());
    }


    /**
     * Reads the object in JSON.
     *
     * @param value         The value to read.
     * @param typeReference The type reference when reading.
     * @param <T>           The type of object being handled.
     * @return The result object.
     */
    protected <T> T readJson(String value, TypeReference<T> typeReference) {
        return JsonUtils.readJson(value, typeReference);
    }

    /**
     * Writes the object in JSON.
     *
     * @param obj The object to write in JSON.
     * @return The result in JSON.
     */
    protected String writeJson(Object obj) {
        return JsonUtils.writeJson(obj);
    }

    /**
     * Reads the given object in JSON.
     *
     * @param value The value to read.
     * @param klass The result class.
     * @param <T>   The type of object being read.
     * @return The result object.
     */
    protected <T> T readJson(String value, Class<T> klass) {
        return JsonUtils.readJson(value, klass);
    }

    protected String getAccessToken() throws Exception {
        return getAccessToken(testUser.getUsername(), testUserPassword);
    }

    protected String getAccessToken(String username, String password) throws Exception {
        return getTokens(username, password).get("access_token");
    }

    protected Map<String, String> getTokens() throws Exception {
        return getTokens(testUser.getUsername(), testUserPassword);
    }

    protected Map<String, String> getTokens(String username, String password) throws Exception {
        return readJson(mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization", "Basic d2ViYXBwOnRlc3Q=")
                .content(String.format("grant_type=password&username=%s&password=%s", username, password)))
                .andReturn()
                .getResponse()
                .getContentAsString(), new TypeReference<Map<String, String>>() {
        });
    }

}
