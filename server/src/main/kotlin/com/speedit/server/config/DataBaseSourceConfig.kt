package com.speedit.server.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableJpaRepositories
@EnableTransactionManagement
class DataBaseSourceConfig (@Value("\${DATABASE_HOST}") val host:String,
                            @Value("\${DATABASE_USER}") val userName: String,
                            @Value("\${DATABASE_PASSWORD}") val password: String,
                            @Value("\${DATABASE_NAME}") val dbName: String) {
    @Bean
    fun datasource(): DataSource {
        return DataSourceBuilder.create()
            .url(this.getUrl())
            .username(this.userName)
            .password(this.password)
            .driverClassName("com.mysql.cj.jdbc.Driver")
            .build()
    }

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val vendorAdapter = HibernateJpaVendorAdapter()
        vendorAdapter.setGenerateDdl(true);

        val factory = LocalContainerEntityManagerFactoryBean();
        factory.jpaVendorAdapter = vendorAdapter;
        factory.setPackagesToScan("com.speedit.server");
        factory.dataSource = datasource();
        return factory;
    }

    private fun getUrl(): String {
        return "jdbc:mysql://${this.host}/${this.dbName}?serverTimezone=UTC&characterEncoding=UTF-8"
    }
}