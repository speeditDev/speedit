package com.speedit.server.common


interface SocialAPIProvider {
    data class VerifiedResult(val id: String, val email: String? = null)

    fun verifyGoogleAccount(idToken: String): VerifiedResult;

    fun verifyKakaoAccount(accessToken: String): VerifiedResult;

    fun verifyNaverAccount(accessToken: String) : VerifiedResult;

    fun verifyAppleAccount(accessToken: String) : VerifiedResult;
}

