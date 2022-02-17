package com.speedit.server.common

import com.fasterxml.jackson.databind.JsonNode
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.time.Duration


@Component
class SocialAPI(
    @Value("\${google_oauth2_client_id}") private val googleClientId: String,
) {
    companion object{
        private const val KAKAO_ACCOUNT_API_URL = "kapi.kakao.com/v2/user/me"
    }

    private val googleVerifier: GoogleIdTokenVerifier = GoogleIdTokenVerifier.Builder(
        NetHttpTransport(),
        GsonFactory())
        .setAudience(listOf(googleClientId))
        .build()



    fun verifyGoogleAccount(idToken: String): String? {
        val verified: GoogleIdToken = googleVerifier.verify(idToken)
            ?: throw RuntimeException("잘못된 토큰입니다.")

        return verified.payload.email ?: throw RuntimeException("구글 인증 정보에 Email이 없습니다")
    }

    fun verifyKakaoAccount(accessToken: String): String? {
        val restTemplate = RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(3))
            .setReadTimeout(Duration.ofSeconds(3))
            .build();

        val propertyKey = "property_keys=[\"kakao_account.email\"]"
        val httpHeaders = HttpHeaders()
        httpHeaders.set("Authorization", "KakaoAK $accessToken")

        val httpEntity = HttpEntity(propertyKey, httpHeaders)

        val response =
            restTemplate.exchange(KAKAO_ACCOUNT_API_URL, HttpMethod.POST, httpEntity, JsonNode::class.java)

        if (response.statusCode != HttpStatus.OK) {
            throw RuntimeException("잘못된 토큰입니다.")
        }

        return response.body?.get("kakao_account")?.get("email")?.textValue()
            ?: throw RuntimeException("카카오 인증 정보에 Email이 없습니다.")
    }

    fun verifyNaverAccount(accessToken: String) {
        TODO()
    }

    fun verifyAppleAccount(accessToken: String) {
        TODO()
    }
}

