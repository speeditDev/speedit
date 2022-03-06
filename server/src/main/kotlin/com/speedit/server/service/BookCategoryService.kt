package com.speedit.server.service

import com.speedit.server.domain.BookCategory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BookCategoryService {
    fun findAllBookCategories(pageable: Pageable): Page<BookCategory>
}