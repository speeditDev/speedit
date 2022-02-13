package com.speedit.server.dto.auth

data class TokenDto (
    val accessToken: String,
    val refreshToken: String,
        )