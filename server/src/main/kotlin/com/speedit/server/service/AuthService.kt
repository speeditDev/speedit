package com.speedit.server.service

import com.speedit.server.domain.User
import com.speedit.server.dto.auth.*

interface AuthService {
    /**
     * 소셜 로그인
     */
    fun signIn(signInInput: SignInInput): SignInResult


    // access token 으로 로그인
    fun signInWithAccessToken(accessToken: String): SignInWithAccessTokenResult

    // 토큰 재발급
    fun refreshAccessToken(refreshToken: String): RefreshAccessTokenResult

    /**
     * 회원가입
     */
    fun signUp(signUpDto: SignUpInput): SignUpResult

    // 닉네임 중복여부 체크
    fun checkNickName(nickName: String): Boolean
    fun lockNickName(nickName: String): Long


    fun signOut(): Void
}

