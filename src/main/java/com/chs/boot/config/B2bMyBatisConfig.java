package com.chs.boot.config;

import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value = "com.chs.boot.b2b", sqlSessionFactoryRef = "b2bSqlSessionFactory")
@EnableTransactionManagement

public class B2bMyBatisConfig {

    @Bean(name = "b2bSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(
        @Qualifier("b2bDataSource") DataSource dataSource, ApplicationContext applicationContext)
        throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(
            applicationContext.getResource("classpath:mybatis-config.xml"));
        sqlSessionFactoryBean.setMapperLocations(
            applicationContext.getResources("classpath:/mapper/b2b/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "b2bSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(
        @Qualifier("b2bSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
