package com.speedit.server.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories(basePackages = ["com.speedit.server.repository"])
@EnableTransactionManagement
class DataSourceConfig (@Value("\${spring.datasource.url}") val host:String,
                        @Value("\${spring.datasource.username}") val userName: String,
                        @Value("\${spring.datasource.password}") val password: String,
                        @Value("\${spring.datasource.driver-class-name}") val driverClassName: String) {

    @Bean
    fun datasource(): DataSource {
        return DataSourceBuilder.create()
            .url(host)
            .username(userName)
            .password(password)
            .driverClassName(driverClassName)
            .build()
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);

        val factory = LocalContainerEntityManagerFactoryBean();
        factory.jpaVendorAdapter = vendorAdapter;
        factory.setPackagesToScan("com.speedit.server");
        factory.dataSource = datasource();
        return factory;
    }

    @Bean
    fun transactionManager(entityManagerFactory: EntityManagerFactory): PlatformTransactionManager {
        val trxManager = JpaTransactionManager()
        trxManager.entityManagerFactory = entityManagerFactory
        return trxManager;
    }
}