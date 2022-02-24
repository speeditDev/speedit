package com.speedit.server.common

import com.speedit.server.dto.auth.Token

interface JwtProvider {
    enum class VerifyResult {
        OK, EXPIRED, INVALID
    }
    fun encode(userId: Long): Token
    fun decode(token: String): Long
    fun verify(token: String): VerifyResult

}