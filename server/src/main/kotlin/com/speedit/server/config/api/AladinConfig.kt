package com.speedit.server.config.api

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "book.api.aladin")
data class AladinConfig (
    var baseUrl: String = "https://www.aladin.co.kr/ttb/api",
    var ttbKey: String = "ttbrolroralra1814001",
    var apiVersion: String = "20131101"
) {

}