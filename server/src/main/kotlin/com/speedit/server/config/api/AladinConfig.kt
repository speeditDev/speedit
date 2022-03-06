package com.speedit.server.config.api

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "book.api.aladin")
data class AladinConfig (
    val baseUrl: String,
    val ttbKey: String,
    val apiVersion: String
)