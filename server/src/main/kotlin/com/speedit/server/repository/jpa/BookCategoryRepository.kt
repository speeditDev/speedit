package com.speedit.server.repository.jpa

import com.speedit.server.domain.BookCategory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface BookCategoryRepository: JpaRepository<BookCategory, Long>  {
    fun findByName(name: String, pageable: Pageable): Page<BookCategory>
}