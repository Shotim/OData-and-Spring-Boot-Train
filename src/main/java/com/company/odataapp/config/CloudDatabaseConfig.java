package com.company.odataapp.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@Profile("cloud")
public class CloudDatabaseConfig extends AbstractCloudConfig {

    @Bean
    public DataSource dataSource(@Value("${vcap.services.hanadb.credentials.url}") final String url,
                                 @Value("${vcap.services.hanadb.credentials.user}") final String user,
                                 @Value("${vcap.services.hanadb.credentials.password}") final String password) {

        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName(com.sap.db.jdbc.Driver.class.getName())
                .url(url)
                .username(user)
                .password(password)
                .build();
    }

    @Override
    public Properties properties() {
        Properties properties = new Properties();

        try {
            properties.load(new ClassPathResource("classpath:application-cf.properties").getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
}