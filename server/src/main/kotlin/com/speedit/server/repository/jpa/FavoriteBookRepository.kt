package com.speedit.server.repository.jpa

import com.speedit.server.domain.FavoriteBook
import org.springframework.data.jpa.repository.JpaRepository

interface FavoriteBookRepository: JpaRepository<FavoriteBook, Long> {
}