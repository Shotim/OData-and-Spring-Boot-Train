package com.company.odataapp.config;

import org.springframework.cloud.config.java.AbstractCloudConfig;

//@Configuration
//@Profile("cloud")
public class CloudDatabaseConfig extends AbstractCloudConfig {
//
//    @Bean
//    public DataSource dataSource(@Value("${hana.url}") final String url,
//                                 @Value("${hana.user}") final String user,
//                                 @Value("${hana.password}") final String password) {
//
//
//        return DataSourceBuilder.create()
//                .type(HikariDataSource.class)
//                .driverClassName(com.sap.cloud)
//                .url(url)
//                .username(user)
//                .password(password)
//                .build();
//
//    }
}