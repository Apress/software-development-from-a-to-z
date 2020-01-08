package com.example.service.config;

import com.example.persistence.config.PersistenceLayerConfig;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configures service module.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@PropertySource("classpath:persistence-layer.properties")
@ComponentScan("com.example.service")
@Configuration
@Import({
        PersistenceLayerConfig.class
})
public class ServiceLayerConfig {

    /**
     * Configures a bean with {@link BCryptPasswordEncoder} encoder.
     *
     * @return The bean.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
