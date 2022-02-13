package com.speedit.server.dto.auth

import com.speedit.server.domain.enums.Sex
import com.speedit.server.domain.enums.SocialAccountType
import java.time.LocalDate

data class SignUpDto(
    val socialAccountType: SocialAccountType,
    val socialAccountId: String,
    val nickName: String,
    val thumbnail: String,
    val sex: Sex,
    val birth: LocalDate,
    val email: String,
    val companyName: String,
    val companyEmail: String?,
    val allowedPrivacyTerm: Boolean,
    val allowedUsedTerm: Boolean
    )