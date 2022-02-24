package com.speedit.server.common.impl

import com.speedit.server.common.JwtProvider
import com.speedit.server.dto.auth.Token
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtProviderImpl(@Value("\${jwt.secret}") private val jwtSecretKey: String) : JwtProvider {
    private val secretKey: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecretKey))

    companion object {
        // ms * 초 * 분 * 시간 * 일자
        private const val ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;
        private const val REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 30;
        private const val ISSUER = "book-plate.kr"
        private const val USER_ID_CLAIM = "USER_ID"
    }

    /**
     * JWT 생성
     */
    override fun encode(userId: Long): Token {
        val now: Long = Date().time

        val accessTokenExpiredIn =  Date(now + ACCESS_TOKEN_EXPIRE_TIME)
        val refreshTokenExpiredIn = Date(now + REFRESH_TOKEN_EXPIRE_TIME)

        val accessToken: String = Jwts
            .builder()
            .setIssuer(ISSUER)
            .claim(USER_ID_CLAIM, userId.toString())
            .setSubject(userId.toString())
            .setExpiration(accessTokenExpiredIn)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()

        val refreshToken: String = Jwts
            .builder()
            .setIssuer(ISSUER)
            .claim(USER_ID_CLAIM, userId.toString())
            .setExpiration(refreshTokenExpiredIn)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()

        return Token(accessToken, refreshToken)
    }

    /**
     * JWT 내 USER_ID 추출
     */
    override fun decode(token: String): Long {
        val claim: Claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJwt(token).body

        return claim[USER_ID_CLAIM].toString().toLong()
    }

    /**
     * JWT 검증
     */
    override fun verify(token: String): JwtProvider.VerifyResult  {
        return try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parse(token)
            JwtProvider.VerifyResult.OK
        } catch (e: ExpiredJwtException) {
            JwtProvider.VerifyResult.EXPIRED
        } catch (e: JwtException) {
            JwtProvider.VerifyResult.INVALID
        }
    }
}