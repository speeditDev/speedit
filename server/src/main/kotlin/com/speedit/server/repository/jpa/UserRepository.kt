package com.speedit.server.repository.jpa

import com.speedit.server.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun findFirstBySocialAccountId(socialAccountId: String): User?
    fun existsByEmail(email: String): Boolean
    fun existsByNickName(nickName: String): Boolean

    @Transactional
    @Modifying
    @Query(
        value = "TRUNCATE TABLE User",
        nativeQuery = true
    )
    fun truncate()
}