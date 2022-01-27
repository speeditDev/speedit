package com.speedit.api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DataSourceConfig (@Value("\${DATABASE_HOST}") val host:String,
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

    private fun getUrl(): String {
        return "jdbc:mysql://${this.host}/${this.dbName}?serverTimezone=UTC&characterEncoding=UTF-8"
    }
}