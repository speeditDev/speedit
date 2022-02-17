package com.speedit.server.repository

import com.speedit.server.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean

    fun existsByNickName(nickName: String): Boolean
}