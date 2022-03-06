package com.speedit.server.service.impl

import com.speedit.server.repository.jpa.BookCategoryRepository
import com.speedit.server.repository.jpa.annotation.DataJpaTestConfig
import com.speedit.server.service.BookCategoryService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.test.context.jdbc.Sql
import javax.annotation.PostConstruct

@DataJpaTestConfig
@Sql(value = ["classpath:sql/data-mysql.sql"])
class BookCategoryServiceImplTest {
    @Autowired
    lateinit var bookCategoryRepository: BookCategoryRepository
    lateinit var bookCategoryService: BookCategoryService

    @PostConstruct
    fun postConstruct() {
        bookCategoryService = BookCategoryServiceImpl(bookCategoryRepository)
    }

    @Test
    fun findAllBookCategories() {
        val findAllBookCategories = bookCategoryService.findAllBookCategories(Pageable.unpaged())

        assertThat(findAllBookCategories)
            .isNotEmpty

        println("AllBookCategoryList Size: ${findAllBookCategories.size}")
    }
}