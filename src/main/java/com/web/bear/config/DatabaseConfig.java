package com.web.bear.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
public class DatabaseConfig {

    //@Value("${spring.datasource.driverClassName}")
    private String driverClassName;
    //@Value("${spring.datasource.maxActive}")
    private int maxActive;
    //@Value("${spring.datasource.url}")
    private String dbUrl;

    //@Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(driverClassName);
        config.setMaximumPoolSize(maxActive);
        config.setJdbcUrl(dbUrl);
        return new HikariDataSource(config);
    }
}
