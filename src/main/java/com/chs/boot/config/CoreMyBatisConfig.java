package com.chs.boot.config;


import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value = {"com.chs.boot.pub",
    "com.chs.boot.sample",
    "com.chs.boot.gerp.core"
}, sqlSessionFactoryRef = "coreSqlSessionFactory")

@EnableTransactionManagement

public class CoreMyBatisConfig {

    @Primary
    @Bean(name = "coreSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
        @Qualifier("coreDataSource") DataSource dataSource, ApplicationContext applicationContext)
        throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(
            applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(
            applicationContext.getResources("classpath:/mapper/core/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "coreSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
        @Qualifier("coreSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
