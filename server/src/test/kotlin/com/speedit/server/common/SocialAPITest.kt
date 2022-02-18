package com.speedit.server.common

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

internal class SocialAPITest {

    private val socialAPI = SocialAPI("91270169157-u2ah895o9qi0fvnu50lkidfeemj7av4d.apps.googleusercontent.com")

    @Test
    @DisplayName("구글 유저정보 확인 api test")
    fun verifyGoogleAccount() {
        val idToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImFjYjZiZTUxZWZlYTZhNDE5ZWM5MzI1ZmVhYTFlYzQ2NjBmNWIzN2MiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiI5MTI3MDE2OTE1Ny11bDZ2OXVlOTMyc292cDBxaGJlNDdkcDFqOHA0cXNncC5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsImF1ZCI6IjkxMjcwMTY5MTU3LXUyYWg4OTVvOXFpMGZ2bnU1MGxraWRmZWVtajdhdjRkLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwic3ViIjoiMTA5MjA0NDUwOTYyMjk0NDc5MTg5IiwiZW1haWwiOiJ3b25ob3Bhcms4OUBnbWFpbC5jb20iLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwibmFtZSI6IkFSSUVTIFAiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUFUWEFKd055YXF4UjRKYkhGeVFIVWJfS2RERFZ0QUl6VnNPVWVhTDhtcjY9czk2LWMiLCJnaXZlbl9uYW1lIjoiQVJJRVMiLCJmYW1pbHlfbmFtZSI6IlAiLCJsb2NhbGUiOiJrbyIsImlhdCI6MTY0NTE4NzE1OCwiZXhwIjoxNjQ1MTkwNzU4fQ.KFSuE3Ews5VVbKHDEsKBkgyKbY8bzD9OLWNKhEfffVbELBPJIChuJgupgLbfKSolJ8SNjYfsiMHR7N-_NOw1dc53Fdi1goEkaHdeATpY8eE2AGpfVNu0QOsbSasJp8PDCo-ns51lf1wBLwkYZIaAxrnbEvu6wDoLlM7KaBFO6lraQlF4Of_dhI-VBBIkIfiTJiRekqEmyHMpe-3uj-oG7YNtWJ_BCEKR6NvGSBCZm_eSsmGlSku4wir4e9_hX4s_Bh9X-E3bKriUMIMooSYhsRF_iAPAN1kay1AKnEOhDb-rNU8nHWME5WOy4mfpFjcp3pHGg3il63QgrQ5vvQMQfA";
        val email = socialAPI.verifyGoogleAccount(idToken)
        val expectEmail = "wonhopark89@gmail.com"
        assertEquals(expectEmail, email)
    }

    @Test
    @DisplayName("카카오 유저정보 확인 api test")
    fun verifyKakaoAccount() {
        val accessToken = "jX53p99KjNqGeZfa2tpC5xhoPD_iB1h3__pPJQo9dJkAAAF_DNonkg"
        val (id, email) = socialAPI.verifyKakaoAccount(accessToken);
    }

    @Test
    @DisplayName("네이버 유저정보 확인 api test")
    fun verifyNaverAccount() {
    }

    @Test
    @DisplayName("애플 유저정보 확인 api test")
    fun verifyAppleAccount() {
    }
}