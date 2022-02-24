package com.speedit.server

import graphql.kickstart.servlet.apollo.ApolloScalars
import graphql.schema.GraphQLScalarType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableJpaAuditing
class ServerApplication {
    @PostConstruct
    fun started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
    }

    // 파일 업로드용 스칼라 파일
    @Bean
    fun uploadScalarType(): GraphQLScalarType {
        return ApolloScalars.Upload;
    }
}

fun main(args: Array<String>) {
    runApplication<ServerApplication>(*args)
}
