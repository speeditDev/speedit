package com.speedit.server.graphql

import com.speedit.server.dto.auth.*
import com.speedit.server.service.AuthService
import graphql.kickstart.tools.GraphQLMutationResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import javax.validation.Valid
import javax.validation.constraints.NotBlank


@Component
@Validated
class AuthMutationResolver constructor(
    @Autowired val authService: AuthService
): GraphQLMutationResolver {

    fun signIn(signInInput: SignInInput): SignInResult {
        return authService.signIn(signInInput)
    }

    fun signInWithAccessToken(@Valid @NotBlank accessToken: String): SignInWithAccessTokenResult {
        return authService.signInWithAccessToken(accessToken)
    }

    fun refreshAccessToken(@Valid @NotBlank refreshToken: String): RefreshAccessTokenResult {
        return authService.refreshAccessToken(refreshToken)
    }

    fun signUp(@Valid signUpInput: SignUpInput): SignUpResult {
        return authService.signUp(signUpInput)
    }
}