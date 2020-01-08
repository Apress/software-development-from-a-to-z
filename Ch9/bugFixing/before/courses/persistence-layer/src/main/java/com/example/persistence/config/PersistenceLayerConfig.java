package com.example.persistence.config;

import com.google.common.base.Throwables;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * Configures the Spring Data Jpa module.
 *
 * @author Rui Vilao (rpvilao@gmail.com)
 */
@PropertySource("classpath:persistence-layer.properties")
@ComponentScan("com.example.persistence")
@EnableTransactionManagement
@EnableJpaRepositories(value = "com.example.persistence.repository")
@Configuration
public class PersistenceLayerConfig {
    @Autowired
    protected Environment environment;

    @Bean
    public DataSource dataSource() {
        final HikariDataSource dataSource = new HikariDataSource();

        final String jdbc;
        final URI dbUri;
        if (System.getenv("DATABASE_URL") != null) {
            try {
               dbUri = new URI(System.getenv("DATABASE_URL"));
            } catch (URISyntaxException e) {
                throw Throwables.propagate(e);
            }

            jdbc = "jdbc:postgresql://"
                    + dbUri.getHost() + ':'
                    + dbUri.getPort() + dbUri.getPath()
                    + "?sslmode=require"
                    + "&user=" + dbUri.getUserInfo().split(":")[0]
                    + "&password=" + dbUri.getUserInfo().split(":")[1];
        } else {
            jdbc = environment.getProperty("jdbc.url");
        }
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(jdbc);
        dataSource.setUsername(environment.getProperty("jdbc.user"));
        dataSource.setPassword(environment.getProperty("jdbc.pass"));

        return dataSource;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return txManager;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(new String[] {"com.example.persistence.entity"});
        entityManagerFactoryBean.setJpaProperties(new Properties() {{
            setProperty("hibernate.dialect", environment.getProperty("hibernate.dialect"));
            if (System.getenv("DATABASE_URL") != null) {
                setProperty("hibernate.default_schema", "public");
            }
        }});

        return entityManagerFactoryBean;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
