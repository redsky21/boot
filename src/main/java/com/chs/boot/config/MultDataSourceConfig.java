package com.chs.boot.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MultDataSourceConfig {
    @Bean
    @Primary
    @Qualifier("coreHikariConfig")
    @ConfigurationProperties(prefix="spring.datasource.hikari.core")
    public HikariConfig coreHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @Primary
    @Qualifier("coreDataSource")
    public DataSource coreDataSource() throws Exception {
        return new HikariDataSource(coreHikariConfig());
    }
    @Bean
    @Qualifier("b2bHikariConfig")
    @ConfigurationProperties(prefix="spring.datasource.hikari.b2b")
    public HikariConfig b2bHikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @Qualifier("b2bDataSource")
    public DataSource b2bDataSource() throws Exception {
        return new HikariDataSource(b2bHikariConfig());
    }
}
