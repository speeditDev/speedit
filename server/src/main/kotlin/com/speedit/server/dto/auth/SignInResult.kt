package com.speedit.server.dto.auth

/**
 * signIn response
 * code: 결과
 *   "ok"           -> 정상토큰발급
 *   "non-user"     -> 미가입
 *   "invalid"      -> 인증 실패
 */
data class SignInResult(
    val status: String,
    val token: Token? = null,
    val message: String? = null
)
