package com.speedit.api.repository

import com.speedit.api.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Int> {
    
}