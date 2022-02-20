package com.speedit.server.service.impl

import com.speedit.server.common.SocialAPI
import com.speedit.server.domain.User
import com.speedit.server.dto.auth.SignUpDto
import com.speedit.server.repository.jpa.UserRepository
import com.speedit.server.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.transaction.Transactional

@Service
class AuthServiceImpl @Autowired constructor(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val socialAPI: SocialAPI
) : AuthService {
    override fun signUp(signUpDto: SignUpDto): User {
        TODO()
    }

    private fun checkGoogleEmail() {
        TODO()
    }

    private fun checkKaKaoEmail() {
        TODO()
    }

    override fun checkNickName(nickName: String): Boolean {
        return userRepository.existsByNickName(nickName)
    }

    override fun checkEmail(email: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun signIn(socialAccountId: String, token: String, accountType: String): String {
        val accountId: String;

        when (accountType) {
            "KAKAO" -> {
                val (id) = socialAPI.verifyKakaoAccount(token)
                accountId = "KAKAO_$id"
            }
            "NAVER" -> {
                val (id) = socialAPI.verifyKakaoAccount(token)
                accountId = "NAVER_$id"
            }
            "GOOGLE" -> {
                val (id) = socialAPI.verifyKakaoAccount(token)
                accountId = "GOOGLE_$id"
            }
            "APPLE" -> {
                val (id) = socialAPI.verifyKakaoAccount(token)
                accountId = "APPLE_$id"
            }
        }
        TODO()
    }

    override fun signOut(): Void {
        TODO("Not yet implemented")
    }
}