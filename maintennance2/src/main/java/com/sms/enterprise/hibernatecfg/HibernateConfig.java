package com.sms.enterprise.hibernatecfg;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
 
@Configuration
public class HibernateConfig {
 
    @Autowired
    private Environment env;
 
    @Bean
    public LocalSessionFactoryBean sessionFactory() throws SQLException {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(env.getProperty("packagesToScan"));
        sessionFactory.setHibernateProperties(hibernateProperties());
 
        return sessionFactory;
    }
 
    @Bean
    public DataSource dataSource() throws SQLException {
    	BasicDataSource ds=new BasicDataSource();
        ds.setDriverClassName(env.getProperty("datasource.driver-class-name"));
        ds.setUrl(env.getProperty("datasource.url"));
        ds.setUsername(env.getProperty("datasource.username"));
        ds.setPassword(env.getProperty("datasource.password"));
        ds.setInitialSize(0);
        ds.setMaxIdle(5);
        ds.setMaxTotal(5);
        ds.setMinIdle(0);
        ds.setMaxWaitMillis(10000);
        return ds;
    }
 
    private final Properties hibernateProperties() {
        Properties hibernate = new Properties();
        hibernate.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        hibernate.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
        hibernate.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        return hibernate;
    }
}