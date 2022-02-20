package com.speedit.server.repository.jpa

import com.speedit.server.domain.Book
import com.speedit.server.domain.BookCategory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository: JpaRepository<Book, Long> {
    fun findByBookCategory(bookCategory: BookCategory, pageable: Pageable): Page<Book>
}