package com.speedit.server.dto.auth

import javax.validation.constraints.NotBlank

data class SignInInput (
    @field:NotBlank val socialAccountId: String,
    @field:NotBlank val socialToken: String,
    @field:NotBlank val accountType: String
)
