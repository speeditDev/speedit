package com.speedit.server.service.impl

import com.speedit.server.common.JwtProvider
import com.speedit.server.common.SocialAPIProvider
import com.speedit.server.domain.User
import com.speedit.server.domain.enums.Sex
import com.speedit.server.domain.enums.SocialAccountType
import com.speedit.server.dto.auth.*
import com.speedit.server.repository.jpa.UserRepository
import com.speedit.server.service.AuthService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils

@Service
class AuthService constructor(
    @Autowired private val userRepository: UserRepository,
    @Autowired private val socialAPI: SocialAPIProvider,
    @Autowired private val jwtProvider: JwtProvider,
) : AuthService {
    companion object {
        val logger = LoggerFactory.getLogger(AuthService::class.java)
    }

    override fun signIn(signInInput: SignInInput): SignInResult {
        val accountId: String
        try {
            accountId = this.getSocialAccountId(signInInput.accountType, signInInput.socialToken)
        } catch (e: RuntimeException) {
            logger.info(e.stackTraceToString())
            return SignInResult("invalid", message = e.message)
        }


        // 유저 조회
        val user = userRepository.findFirstBySocialAccountId(accountId)
        val userId = user?.userId ?: return SignInResult(status = "non-user", message = "계정이 존재하지 않습니다")

        // 토큰 생성
        val token = jwtProvider.encode(userId)

        return SignInResult(status = "ok", token = token)
    }

    // accessToken 검증
    override fun signInWithAccessToken(accessToken: String): SignInWithAccessTokenResult {
        val verifiedResult = jwtProvider.verify(accessToken)

        return when (verifiedResult) {
            JwtProvider.VerifyResult.OK -> {
                SignInWithAccessTokenResult("ok")
            }
            JwtProvider.VerifyResult.EXPIRED -> {
                SignInWithAccessTokenResult("expired")
            }
            JwtProvider.VerifyResult.INVALID -> {
                SignInWithAccessTokenResult("invalid")
            }
        }
    }

    // accessToken 만료시 refresh 토큰을 이용한 재발급
    override fun refreshAccessToken(refreshToken: String): RefreshAccessTokenResult {
        val verifiedResult = jwtProvider.verify(refreshToken)

        return when (verifiedResult) {
            JwtProvider.VerifyResult.OK -> {
                val userId = jwtProvider.decode(refreshToken)
                val newToken = jwtProvider.encode(userId)
                RefreshAccessTokenResult(status = "ok", token = newToken)
            }
            JwtProvider.VerifyResult.EXPIRED -> {
                RefreshAccessTokenResult("expired")

            }
            JwtProvider.VerifyResult.INVALID -> {
                RefreshAccessTokenResult("invalid")
            }
        }
    }

    @Transactional
    override fun signUp(signUpInput: SignUpInput): SignUpResult {
        val socialAccountType: SocialAccountType;
        val socialAccountId: String;

        try {
            socialAccountType = SocialAccountType.valueOf(signUpInput.socialAccountType);
            socialAccountId = this.getSocialAccountId(signUpInput.socialAccountType, signUpInput.socialToken)
        } catch (e: RuntimeException) {
            logger.error(e.stackTraceToString())
            return SignUpResult("fail", message = e.message)
        }

        // 기존 유저 확인
        val existUser = userRepository.findFirstBySocialAccountId(socialAccountId)
        if (existUser != null) {
            val token = jwtProvider.encode(existUser.userId!!)
            return SignUpResult("already_exist", token, "이미 회원가입이 되어있습니다")
        }


        // 유저 저장
        val user = User(
            socialAccountType = socialAccountType,
            socialAccountId = socialAccountId,
            nickName = signUpInput.nickName,
            thumbnail = signUpInput.thumbnail,
            allowedUsedTerm = signUpInput.allowedUsedTerm,
            allowedPrivacyTerm = signUpInput.allowedPrivacyTerm,
            birth = signUpInput.birth,
            sex = if (StringUtils.hasText(signUpInput.sex)) Sex.valueOf(signUpInput.sex!!) else null ,
            email = signUpInput.email,
            companyName = signUpInput.companyName,
            companyEmail = signUpInput.companyEmail,
        )


        val newUser = userRepository.save(user)

        // TODO companyEmail 존재시 email 발송

        val token = jwtProvider.encode(newUser.userId!!)

        return SignUpResult("ok", token)
    }

    private fun getSocialAccountId(accountType: String, socialToken: String): String {
        return when (accountType) {
            "KAKAO" -> {
                val (id) = socialAPI.verifyKakaoAccount(socialToken)
                "KAKAO_$id"
            }
            "NAVER" -> {
                val (id) = socialAPI.verifyKakaoAccount(socialToken)
                "NAVER_$id"
            }
            "GOOGLE" -> {
                val (id) = socialAPI.verifyKakaoAccount(socialToken)
                "GOOGLE_$id"
            }
            "APPLE" -> {
                val (id) = socialAPI.verifyKakaoAccount(socialToken)
                "APPLE_$id"
            }
            else -> {
                throw IllegalArgumentException("사용불가능한 AccountType 입니다.")
            }
        }
    }

    override fun checkNickName(nickName: String): Boolean {
        return userRepository.existsByNickName(nickName)
    }

    override fun lockNickName(nickName: String): Long {
        TODO("Not yet implemented")
    }

    override fun signOut(): Void {
        TODO("Not yet implemented")
    }
}