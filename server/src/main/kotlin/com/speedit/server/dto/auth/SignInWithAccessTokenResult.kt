package com.speedit.server.dto.auth

data class SignInWithAccessTokenResult(
    /**
     * "ok"         - 인증 성공
     * "expired"    - access token 만료
     * "invalid"    - 인증 실패
     */
    val status: String,
)