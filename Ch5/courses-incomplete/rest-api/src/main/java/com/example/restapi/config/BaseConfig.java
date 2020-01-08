package com.example.restapi.config;

import com.example.service.config.ServiceLayerConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.*;

/**
 * Application base configuration.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({
        MvcConfig.class,
        OAuth2AuthorizationServerConfig.class,
        WebSecurityConfig.class,
        ResourceServiceConfiguration.class,
        ServiceLayerConfig.class
})
@ComponentScan("com.example.restapi")
public class BaseConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
