package com.speedit.server.util

import com.speedit.server.domain.User
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class JwtTokenProvider(@Value("jwt_secret_key") secretKey: String) {
    fun encode() {}

    fun decode() {}
}