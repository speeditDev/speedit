package com.speedit.server.dto.auth

data class RefreshAccessTokenResult(
    /**
     * "ok"         - 인증 성공
     * "invalid"    - 인증 실패
     */
    val status: String,
    val token: Token? = null,
)