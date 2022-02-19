package com.speedit.server.repository

import com.speedit.server.domain.enums.BookCategory
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import java.util.stream.LongStream

import org.assertj.core.api.Assertions.*


@TestPropertySource("classpath:/application.yaml")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookCategoryRepositoryTest {
    @Autowired
    lateinit var bookCategoryRepository: BookCategoryRepository

    companion object {
        fun generateNewBookCategoryCode(bookCategoryRepository: BookCategoryRepository): Long {
            return LongStream.range(1, Long.MAX_VALUE)
                .filter { id -> bookCategoryRepository.findById(id).isEmpty }
                .findFirst()
                .orElseThrow()
        }

        fun createBookCategory(bookCategoryRepository: BookCategoryRepository, name: String): BookCategory {
            return BookCategory(generateNewBookCategoryCode(bookCategoryRepository), name)
        }

        fun saveBookCategory(bookCategoryRepository: BookCategoryRepository, name: String): BookCategory {
            return bookCategoryRepository.save(createBookCategory(bookCategoryRepository, name))
        }
    }

    @RepeatedTest(10)
    fun test_save_book_category() {
        // Given
        val bookCategory = createBookCategory(
            bookCategoryRepository,
            "IT-Programming"
        )

        // When

        // Then
        val savedBookCategory = bookCategoryRepository.save(bookCategory)

        assertThat(savedBookCategory)
            .isNotNull
            .isEqualTo(bookCategory)
    }

    @RepeatedTest(10)
    fun test_modify_book_category() {
        // Given
        val bookCategory = saveBookCategory(
            bookCategoryRepository,
            "IT-Programming"
        )

        val findBookCategory = bookCategoryRepository.findById(bookCategory.code)
        assertThat(findBookCategory)
            .isPresent
            .get()
            .isNotNull
            .isEqualTo(bookCategory)

        val modifiedBookCategory = findBookCategory.get()
        modifiedBookCategory.name = "Modifed Category Name"

        // When
        val savedModifiedBookCategory = bookCategoryRepository.save(modifiedBookCategory)

        // Then
        assertThat(savedModifiedBookCategory)
            .isNotNull
            .isEqualTo(modifiedBookCategory)
    }

    @RepeatedTest(10)
    fun test_find_book_category_by_id() {
        // Given
        val bookCategory = saveBookCategory(
            bookCategoryRepository,
            "IT-Programming"
        )

        // When
        val findBookCategory = bookCategoryRepository.findById(bookCategory.code)

        // Then
        assertThat(findBookCategory)
            .isPresent
            .get()
            .isEqualTo(bookCategory)
    }

    @ParameterizedTest
    @ValueSource(strings = ["it", "프로그래밍", "운영체제"])
    fun test_find_book_category_by_name(bookCategoryName: String) {
        // Given
        val bookCategory = saveBookCategory(
            bookCategoryRepository,
            bookCategoryName
        )

        // When
        val bookCategoryList = bookCategoryRepository.findByName(bookCategoryName)

        // Then
        assertThat(bookCategoryList)
            .isNotNull
            .isNotEmpty
            .contains(bookCategory)
    }

    @RepeatedTest(10)
    fun test_delete_book_category() {
        // Given
        val bookCategory = saveBookCategory(
            bookCategoryRepository,
            "IT-Programming"
        )

        // When
        val deleteBookCategoryId = bookCategory.code
        bookCategoryRepository.delete(bookCategory)

        // Then
        assertThat(bookCategoryRepository.findById(deleteBookCategoryId))
            .isEmpty
    }

    @RepeatedTest(10)
    fun test_delete_book_category_by_id() {
        // Given
        val bookCategory = saveBookCategory(
            bookCategoryRepository,
            "IT-Programming"
        )

        // When
        val deleteBookCategoryId = bookCategory.code
        bookCategoryRepository.deleteById(deleteBookCategoryId)


        // Then
        assertThat(bookCategoryRepository.findById(deleteBookCategoryId))
            .isEmpty
    }
}