package com.speedit.server.service.impl

import com.speedit.server.domain.User
import com.speedit.server.dto.auth.SignUpDto
import com.speedit.server.repository.UserRepository
import com.speedit.server.service.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class AuthServiceImpl @Autowired constructor(private val userRepository: UserRepository) : AuthService {
    override fun signUp(signUpDto: SignUpDto): User {
        val isUserExist = userRepository.existsByEmail(signUpDto.email);

        if (isUserExist) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다.")
        }

        val insertUser = User(
            userId = null,
            nickName = signUpDto.nickName,
            sex = signUpDto.sex,
            birth = signUpDto.birth,
            thumbnail = signUpDto.thumbnail,
            email = signUpDto.email,
            companyName = signUpDto.companyName,
            companyEmail = signUpDto.companyEmail,
            allowedPrivacyTerm = signUpDto.allowedPrivacyTerm,
            allowedUsedTerm = signUpDto.allowedUsedTerm,
            isCompanyEmailValid = false,
            state = 1
        )

        val user = userRepository.save(insertUser);

        return user
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

    /**
     * @param socialAccountId
     * @param token
     * @param accountType - 소셜계정타입("KAKAO", "NAVER", "GOOGLE", "APPLE")
     */
    override fun signIn(socialAccountId: String, token: String, accountType: String): String {
        TODO("Not yet implemented")
    }

    override fun signOut(): Void {
        TODO("Not yet implemented")
    }
}