package com.speedit.server.respository

import com.speedit.server.domain.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Long> {
}