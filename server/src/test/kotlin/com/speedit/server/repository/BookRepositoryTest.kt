package com.speedit.server.repository

import com.speedit.server.domain.Book
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource

@TestPropertySource("classpath:/application.yaml")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {
    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var bookCategoryRepository: BookCategoryRepository

    @Test
    fun test_save_book() {
        // Given
        val book = Book(
            9791196834005,
            "<b>백년운동</b>",
            19500,
            "http://book.naver.com/bookdb/book_detail.php?bid=15760180",
            "https://bookthumb-phinf.pstatic.net/cover/157/601/15760180.jpg?type=m1&udate=20220212",
            "정선근",
            17550,
            "아티잔",
            "서울대 의대 재활의학과 정선근 교수는 그의 새로운 책, ‘<b>백년운동</b>’을 통해서 100세 인생을 건강하고 멋지게 살고 싶은 이들에게 그 방법을 의학적으로 제시하고 있다. 정 교수가 몇 년 전에 출간한, 건강분야의 스테디셀러인 ‘ 백년허리’ , ‘백년 목’이 허리와 목디스크 질환에 대한 새로운 치료법을... ",
            BookCategoryRepositoryTest.getRandomBookCategory(bookCategoryRepository)
        )

        // When

        // Then
        val savedBook = bookRepository.save(book)

        assertThat(savedBook)
            .isNotNull
            .isEqualTo(book)
    }

    @Test
    fun test_modify_book() {
        // Given
        val book = Book(
            9791196834005,
            "<b>백년운동</b>",
            19500,
            "http://book.naver.com/bookdb/book_detail.php?bid=15760180",
            "https://bookthumb-phinf.pstatic.net/cover/157/601/15760180.jpg?type=m1&udate=20220212",
            "정선근",
            17550,
            "아티잔",
            "서울대 의대 재활의학과 정선근 교수는 그의 새로운 책, ‘<b>백년운동</b>’을 통해서 100세 인생을 건강하고 멋지게 살고 싶은 이들에게 그 방법을 의학적으로 제시하고 있다. 정 교수가 몇 년 전에 출간한, 건강분야의 스테디셀러인 ‘ 백년허리’ , ‘백년 목’이 허리와 목디스크 질환에 대한 새로운 치료법을... ",
            BookCategoryRepositoryTest.getRandomBookCategory(bookCategoryRepository)
        )

        bookRepository.save(book)
        val findBook = bookRepository.findById(book.isbn)
        assertThat(findBook)
            .isPresent
            .get()
            .isNotNull
            .isEqualTo(book)

        val modifiedBook = findBook.get()
        modifiedBook.description = "modified description"
        modifiedBook.discount = 0

        // When
        val savedModifiedBook = bookRepository.save(modifiedBook)

        // Then
        assertThat(savedModifiedBook)
            .isNotNull
            .isEqualTo(modifiedBook)
    }

    @Test
    fun test_find_book_by_id() {
        // Given
        val book = Book(
            9791196834005,
            "<b>백년운동</b>",
            19500,
            "http://book.naver.com/bookdb/book_detail.php?bid=15760180",
            "https://bookthumb-phinf.pstatic.net/cover/157/601/15760180.jpg?type=m1&udate=20220212",
            "정선근",
            17550,
            "아티잔",
            "서울대 의대 재활의학과 정선근 교수는 그의 새로운 책, ‘<b>백년운동</b>’을 통해서 100세 인생을 건강하고 멋지게 살고 싶은 이들에게 그 방법을 의학적으로 제시하고 있다. 정 교수가 몇 년 전에 출간한, 건강분야의 스테디셀러인 ‘ 백년허리’ , ‘백년 목’이 허리와 목디스크 질환에 대한 새로운 치료법을... ",
            BookCategoryRepositoryTest.getRandomBookCategory(bookCategoryRepository)
        )

        // When
        bookRepository.save(book)
        val findBook = bookRepository.findById(book.isbn)
        // Then
        assertThat(findBook)
            .isPresent
            .get()
            .isEqualTo(book)
    }

    @Test
    fun test_delete_book() {
        // Given
        val book = Book(
            9791196834005,
            "<b>백년운동</b>",
            19500,
            "http://book.naver.com/bookdb/book_detail.php?bid=15760180",
            "https://bookthumb-phinf.pstatic.net/cover/157/601/15760180.jpg?type=m1&udate=20220212",
            "정선근",
            17550,
            "아티잔",
            "서울대 의대 재활의학과 정선근 교수는 그의 새로운 책, ‘<b>백년운동</b>’을 통해서 100세 인생을 건강하고 멋지게 살고 싶은 이들에게 그 방법을 의학적으로 제시하고 있다. 정 교수가 몇 년 전에 출간한, 건강분야의 스테디셀러인 ‘ 백년허리’ , ‘백년 목’이 허리와 목디스크 질환에 대한 새로운 치료법을... ",
            BookCategoryRepositoryTest.getRandomBookCategory(bookCategoryRepository)
        )

        // When
        bookRepository.save(book)
        val deleteBookId = book.isbn
        bookRepository.delete(book)

        val deletedBook = bookRepository.findById(deleteBookId)

        // Then
        assertThat(deletedBook)
            .isEmpty
    }

    @Test
    fun test_delete_book_by_id() {
        // Given
        val book = Book(
            9791196834005,
            "<b>백년운동</b>",
            19500,
            "http://book.naver.com/bookdb/book_detail.php?bid=15760180",
            "https://bookthumb-phinf.pstatic.net/cover/157/601/15760180.jpg?type=m1&udate=20220212",
            "정선근",
            17550,
            "아티잔",
            "서울대 의대 재활의학과 정선근 교수는 그의 새로운 책, ‘<b>백년운동</b>’을 통해서 100세 인생을 건강하고 멋지게 살고 싶은 이들에게 그 방법을 의학적으로 제시하고 있다. 정 교수가 몇 년 전에 출간한, 건강분야의 스테디셀러인 ‘ 백년허리’ , ‘백년 목’이 허리와 목디스크 질환에 대한 새로운 치료법을... ",
            BookCategoryRepositoryTest.getRandomBookCategory(bookCategoryRepository)
        )

        // When
        bookRepository.save(book)
        val deleteBookId = book.isbn
        bookRepository.deleteById(deleteBookId)

        val deletedBook = bookRepository.findById(deleteBookId)

        // Then
        assertThat(deletedBook)
            .isEmpty
    }
}