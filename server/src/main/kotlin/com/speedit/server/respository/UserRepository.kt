package com.speedit.server.respository

import com.speedit.server.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Long> {
    override fun <S : User?> save(entity: S): S {
        TODO("Not yet implemented")
    }

    fun existsByEmail(email: String): Boolean

    fun existsByNickName(nickName: String): Boolean
}