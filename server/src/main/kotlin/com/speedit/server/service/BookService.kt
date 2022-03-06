package com.speedit.server.service

import com.speedit.server.domain.Book
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface BookService {
    fun searchBookByCategory(bookCategoryCode: Long, pageable: Pageable): Page<Book>
    fun searchBookByTitle(title: String, pageable: Pageable): Page<Book>
    fun searchBookByIsbn(isbn: Long)

}