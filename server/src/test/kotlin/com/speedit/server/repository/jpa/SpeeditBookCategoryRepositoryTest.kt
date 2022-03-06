package com.speedit.server.repository.jpa

import com.speedit.server.domain.SpeeditBookCategory
import com.speedit.server.repository.jpa.annotation.DataJpaTestConfig
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import java.util.*
import java.util.stream.LongStream

@DataJpaTestConfig
class SpeeditBookCategoryRepositoryTest {
    @Autowired
    lateinit var speeditBookCategoryRepository: SpeeditBookCategoryRepository

    companion object {
        fun generateNewSpeeditBookCategoryCode(speeditBookCategoryRepository: SpeeditBookCategoryRepository): Long {
            return LongStream.range(1, Long.MAX_VALUE)
                .filter { id -> speeditBookCategoryRepository.findById(id).isEmpty }
                .findAny()
                .orElseThrow()
        }

        fun getAnySpeeditBookCategory(speeditBookCategoryRepository: SpeeditBookCategoryRepository): SpeeditBookCategory {
            return speeditBookCategoryRepository.findAll().stream()
                .findAny()
                .orElse(null)
        }

        fun getOtherSpeeditBookCategory(speeditBookCategoryRepository: SpeeditBookCategoryRepository, speeditBookCategory: SpeeditBookCategory): SpeeditBookCategory {
            return speeditBookCategoryRepository.findAll().stream()
                .filter{ value -> !Objects.equals(speeditBookCategory, value)}
                .findAny()
                .orElse(null)
        }

        fun createSpeeditBookCategory(speeditBookCategoryRepository: SpeeditBookCategoryRepository, name: String): SpeeditBookCategory {
            return SpeeditBookCategory(generateNewSpeeditBookCategoryCode(speeditBookCategoryRepository), name)
        }

        fun saveSpeeditBookCategory(speeditBookCategoryRepository: SpeeditBookCategoryRepository, name: String): SpeeditBookCategory {
            return speeditBookCategoryRepository.save(createSpeeditBookCategory(speeditBookCategoryRepository, name))
        }
    }

    @RepeatedTest(10)
    @DisplayName("SpeeditBookCategory 저장 테스트")
    fun test_save_book_category() {
        // Given
        val speeditBookCategory = createSpeeditBookCategory(
            speeditBookCategoryRepository,
            "IT-Programming"
        )

        // When

        // Then
        val savedBookCategory = speeditBookCategoryRepository.save(speeditBookCategory)

        Assertions.assertThat(savedBookCategory)
            .isNotNull
            .isEqualTo(speeditBookCategory)
    }

    @RepeatedTest(10)
    @DisplayName("SpeeditBookCategory 수정 테스트")
    fun test_modify_book_category() {
        // Given
        val bookCategory = saveSpeeditBookCategory(
            speeditBookCategoryRepository,
            "IT-Programming"
        )

        val findBookCategory = speeditBookCategoryRepository.findById(bookCategory.id)
        Assertions.assertThat(findBookCategory)
            .isPresent
            .get()
            .isNotNull
            .isEqualTo(bookCategory)

        val modifiedBookCategory = findBookCategory.get()
        modifiedBookCategory.name = "Modifed Category Name"

        // When
        val savedModifiedBookCategory = speeditBookCategoryRepository.save(modifiedBookCategory)

        // Then
        Assertions.assertThat(savedModifiedBookCategory)
            .isNotNull
            .isEqualTo(modifiedBookCategory)
    }

    @RepeatedTest(10)
    @DisplayName("SpeeditBookCategory 조회 by id 테스트")
    fun test_find_book_category_by_id() {
        // Given
        val bookCategory = saveSpeeditBookCategory(
            speeditBookCategoryRepository,
            "IT-Programming"
        )

        // When
        val findBookCategory = speeditBookCategoryRepository.findById(bookCategory.id)

        // Then
        Assertions.assertThat(findBookCategory)
            .isPresent
            .get()
            .isEqualTo(bookCategory)
    }

    @ParameterizedTest
    @ValueSource(strings = ["it", "프로그래밍", "운영체제"])
    @DisplayName("SpeeditBookCategory 조회 by name 테스트")
    fun test_find_book_category_by_name(bookCategoryName: String) {
        // Given
        val bookCategory = saveSpeeditBookCategory(
            speeditBookCategoryRepository,
            bookCategoryName
        )

        // When
        val bookCategoryList = speeditBookCategoryRepository.findByName(bookCategoryName, Pageable.unpaged())

        // Then
        Assertions.assertThat(bookCategoryList)
            .isNotNull
            .isNotEmpty
            .contains(bookCategory)
    }

    @ParameterizedTest
    @ValueSource(strings = ["it", "프로그래밍", "운영체제"])
    @DisplayName("SpeeditBookCategory 조회 by name contains 테스트")
    fun test_find_book_category_by_name_contains(bookCategoryName: String) {
        // Given
        val bookCategory = saveSpeeditBookCategory(
            speeditBookCategoryRepository,
            bookCategoryName
        )

        val bookCategory2 = saveSpeeditBookCategory(
            speeditBookCategoryRepository,
            bookCategoryName + "2"
        )

        val bookCategory3 = saveSpeeditBookCategory(
            speeditBookCategoryRepository,
            "Home " + bookCategoryName + "기술"
        )

        // When
        val bookCategoryList = speeditBookCategoryRepository.findByNameContains(bookCategoryName, Pageable.unpaged())

        // Then
        Assertions.assertThat(bookCategoryList)
            .isNotNull
            .isNotEmpty
            .contains(bookCategory, bookCategory2, bookCategory3)
    }

    @RepeatedTest(10)
    @DisplayName("SpeeditBookCategory 삭제 테스트")
    fun test_delete_book_category() {
        // Given
        val bookCategory = saveSpeeditBookCategory(
            speeditBookCategoryRepository,
            "IT-Programming"
        )

        // When
        val deleteBookCategoryId = bookCategory.id
        speeditBookCategoryRepository.delete(bookCategory)

        // Then
        Assertions.assertThat(speeditBookCategoryRepository.findById(deleteBookCategoryId))
            .isEmpty
    }

    @RepeatedTest(10)
    @DisplayName("SpeeditBookCategory 삭제 by id 테스트")
    fun test_delete_book_category_by_id() {
        // Given
        val bookCategory = saveSpeeditBookCategory(
            speeditBookCategoryRepository,
            "IT-Programming"
        )

        // When
        val deleteBookCategoryId = bookCategory.id
        speeditBookCategoryRepository.deleteById(deleteBookCategoryId)


        // Then
        Assertions.assertThat(speeditBookCategoryRepository.findById(deleteBookCategoryId))
            .isEmpty
    }
}