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
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.util.StringUtils
import java.time.Duration


@Component
class SocialAPI(
    @Value("\${google_oauth2_client_id}") private val googleClientId: String,
) {
    companion object{
        private const val KAKAO_ACCOUNT_API_URL = "https://kapi.kakao.com/v2/user/me"
    }

    data class VerifiedResult(val id: String, val email: String?)

    private val googleVerifier: GoogleIdTokenVerifier = GoogleIdTokenVerifier.Builder(
        NetHttpTransport(),
        GsonFactory())
        .setAudience(listOf(googleClientId))
        .build()


    fun verifyGoogleAccount(idToken: String): VerifiedResult {
        val verified: GoogleIdToken = googleVerifier.verify(idToken)
            ?: throw RuntimeException("잘못된 토큰입니다.")

        return VerifiedResult(verified.payload.subject, verified.payload.email)
    }

    fun verifyKakaoAccount(accessToken: String): VerifiedResult {
        // api 호출용 restTemplate 생성
        val restTemplate = RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(3))
            .setReadTimeout(Duration.ofSeconds(3))
            .build();

        // 헤더 지정
        val httpHeaders = HttpHeaders()
        httpHeaders.set("Authorization", "Bearer $accessToken")
        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8")

        // body 생성
        val params: MultiValueMap<String, String> = LinkedMultiValueMap()
        params.set("property_keys", "[\"kakao_account.email\"]")
        val httpEntity = HttpEntity(params, httpHeaders)

        // post 요청
        val response =
            restTemplate.exchange(KAKAO_ACCOUNT_API_URL, HttpMethod.POST, httpEntity, JsonNode::class.java)

        if (response.statusCode != HttpStatus.OK) {
            throw RuntimeException("잘못된 토큰입니다.")
        }

        val id = response.body?.get("id")?.asText() ?: throw RuntimeException("유저 정보를 확인할 수 없습니다");
        val email = response.body?.get("kakao_account")?.get("email")?.asText()

        if (!StringUtils.hasLength(id)) {
            throw RuntimeException("test")
        }

        return VerifiedResult(id, email)
    }

    fun verifyNaverAccount(accessToken: String) : VerifiedResult {
        TODO()
    }

    fun verifyAppleAccount(accessToken: String) : VerifiedResult {
        TODO()
    }
}

