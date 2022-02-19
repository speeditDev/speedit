package com.speedit.server.repository

import com.speedit.server.domain.enums.BookCategory
import org.springframework.data.jpa.repository.JpaRepository

interface BookCategoryRepository: JpaRepository<BookCategory, Long>  {
    fun findByName(name: String): List<BookCategory>
}