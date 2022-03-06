package com.speedit.server.service.impl

import com.speedit.server.config.api.AladinConfig
import com.speedit.server.domain.Book
import com.speedit.server.repository.jpa.BookCategoryRepository
import com.speedit.server.repository.jpa.BookRepository
import com.speedit.server.repository.jpa.annotation.DataJpaTestConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.web.client.RestTemplate
import javax.annotation.PostConstruct

@DataJpaTestConfig
class AladinBookServiceTest {
    @Autowired
    lateinit var bookRepository: BookRepository
    @Autowired
    lateinit var bookCategoryRepository: BookCategoryRepository
    @Autowired
    lateinit var aladinConfig: AladinConfig

    val restTemplate = RestTemplate()

    val aladinBookService: AladinBookService = AladinBookService()

    @PostConstruct
    fun postConstruct() {
        aladinBookService.bookRepository = bookRepository
        aladinBookService.bookCategoryRepository = bookCategoryRepository
        aladinBookService.aladinConfig = aladinConfig
        aladinBookService.restTemplate = restTemplate
    }

    @ParameterizedTest
    @CsvSource(value = ["27660:100:1"], delimiterString = ":")
    fun test(categoryCode: Long, size: Int, page: Int) {
        val searchBookByCategory = aladinBookService.searchBookByCategory(categoryCode, Pageable.ofSize(size).withPage(page))

        println(searchBookByCategory.size)

        assertThat(searchBookByCategory)
            .isNotNull
            .hasSizeLessThanOrEqualTo(size)
            .hasOnlyElementsOfType(Book::class.java)
    }
}