package com.speedit.server.dto.auth

import com.speedit.server.domain.enums.Sex
import com.speedit.server.domain.enums.SocialAccountType
import java.time.LocalDate
import javax.validation.constraints.NotBlank

data class SignUpInput(
    @NotBlank val socialAccountType: String,
    @NotBlank val socialAccountId: String,
    @NotBlank val socialToken: String,
    @NotBlank val nickName: String,
    val thumbnail: String?,
    val sex: String?,
    val birth: LocalDate?,
    val email: String?,
    val companyName: String,
    val companyEmail: String?,
    val allowedPrivacyTerm: Boolean,
    val allowedUsedTerm: Boolean
    )