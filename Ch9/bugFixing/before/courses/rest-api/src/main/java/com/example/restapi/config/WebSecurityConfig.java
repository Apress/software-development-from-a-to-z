package com.example.restapi.config;

import com.example.restapi.auth.DefaultAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Configures the web security.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Configures the user authentication provider.
     *
     * @param auth The authentication builder.
     * @throws Exception If something goes wrong configuring the provider.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(defaultAuthenticationProvider());
    }

    /**
     * Configures the authentication provider.
     *
     * @return The provider.
     */
    @Bean
    public DefaultAuthenticationProvider defaultAuthenticationProvider() {
        return new DefaultAuthenticationProvider();
    }

    /**
     * Gets the authentication manager.
     *
     * @return The authentication manager.
     * @throws Exception
     */
    @Bean(name = "authenticationManagerBean")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
