package com.example.springbootvuetest.config.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@EnableJpaRepositories(
        basePackages = "com.example.springbootvuetest.repository.mssql.erp",
        entityManagerFactoryRef = "erpEntityManager",
        transactionManagerRef = "erpTransactionManager"
)
@Configuration
public class ErpDatasoruce {

    @Primary
    @Bean
    public PlatformTransactionManager erpTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(erpEntityManager().getObject());

        return transactionManager;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean erpEntityManager() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(erpDataSource());
        em.setPackagesToScan("com.example.springbootvuetest.entity.erp");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return em;
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.mssql")
    public DataSource erpDataSource() {
        return DataSourceBuilder.create().build();
    }
}
