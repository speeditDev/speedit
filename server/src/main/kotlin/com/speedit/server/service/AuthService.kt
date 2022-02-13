package com.speedit.server.service

import com.speedit.server.domain.User
import com.speedit.server.dto.auth.SignUpDto

interface AuthService {
    fun signUp(signUpDto: SignUpDto): User
    fun checkEmail(email: String): Boolean
    fun checkNickName(nickName: String): Boolean
    fun signIn(socialAccountId: String, token: String, accountType: String): String
    fun signOut(): Void
}

