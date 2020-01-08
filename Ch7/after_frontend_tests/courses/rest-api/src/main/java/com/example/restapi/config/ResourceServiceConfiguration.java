package com.example.restapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

/**
 * Configures the resource service.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Configuration
@EnableResourceServer
public class ResourceServiceConfiguration extends ResourceServerConfigurerAdapter {

    @Autowired
    private DefaultTokenServices defaultTokenServices;

    @Override
    public void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .regexMatchers("/([^/].*?)/secured/.*").access("hasRole('STUDENT')");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(defaultTokenServices);
    }
}
