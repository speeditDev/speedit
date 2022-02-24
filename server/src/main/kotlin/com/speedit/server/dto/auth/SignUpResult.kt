package com.speedit.server.dto.auth

import com.speedit.server.domain.enums.Sex
import com.speedit.server.domain.enums.SocialAccountType
import java.time.LocalDate

data class SignUpResult(
    /**
     * ok       -> 성공
     * fail     -> 실패
     * already_exist -> 이미 존재하는 계정
     */
    val status: String,
    val token: Token? = null,
    val message: String? = null
)