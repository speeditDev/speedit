package com.speedit.server.dto.auth

import javax.validation.constraints.NotEmpty

data class SignInDto(
    @NotEmpty
    val snsToken: String
)
