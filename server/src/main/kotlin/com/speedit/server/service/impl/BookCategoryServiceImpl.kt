package com.speedit.server.service.impl

import com.speedit.server.domain.BookCategory
import com.speedit.server.repository.jpa.BookCategoryRepository
import com.speedit.server.service.BookCategoryService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookCategoryServiceImpl(
    val bookCategoryRepository: BookCategoryRepository
): BookCategoryService {

    override fun findAllBookCategories(pageable: Pageable): Page<BookCategory> {
        return bookCategoryRepository.findAll(pageable)
    }
}