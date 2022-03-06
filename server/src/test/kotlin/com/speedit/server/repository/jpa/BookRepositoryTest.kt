package com.speedit.server.repository.jpa

import com.speedit.server.domain.Book
import com.speedit.server.domain.BookCategory
import com.speedit.server.repository.jpa.annotation.DataJpaTestConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import java.util.*
import java.util.stream.LongStream

@DataJpaTestConfig
class BookRepositoryTest {
    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var bookCategoryRepository: BookCategoryRepository

    companion object {
        fun generateNewBookIsbn(bookRepository: BookRepository): Long {
            return LongStream.range(1, Long.MAX_VALUE)
                .filter { id -> bookRepository.findById(id).isEmpty }
                .findAny()
                .orElseThrow()
        }

        fun getAnyBookCategory(bookRepository: BookRepository): Book {
            return bookRepository.findAll().stream()
                .findAny()
                .orElse(null)
        }

        fun getOtherBook(bookRepository: BookRepository, book: Book): Book {
            return bookRepository.findAll().stream()
                .filter{ value -> !Objects.equals(book, value)}
                .findAny()
                .orElse(null)
        }

        fun getAnyBook(bookRepository: BookRepository): Book {
            return bookRepository.findAll().stream()
                .findAny()
                .orElse(null)
        }
    }


    fun saveBookCategory(bookCategoryName: String): BookCategory {
        return BookCategoryRepositoryTest.saveBookCategory(bookCategoryRepository, bookCategoryName)
    }

    @BeforeEach
    fun beforeEach() {
        saveBookCategory("book-category-0001")
        saveBookCategory("book-category-0002")
        saveBookCategory("book-category-0003")
        saveBookCategory("book-category-0004")
        saveBookCategory("book-category-0005")
    }

    @Test
    @DisplayName("Book 저장 테스트")
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
            BookCategoryRepositoryTest.getAnyBookCategory(bookCategoryRepository)
        )

        // When

        // Then
        val savedBook = bookRepository.save(book)

        assertThat(savedBook)
            .isNotNull
            .isEqualTo(book)
    }

    @Test
    @DisplayName("Book 수정 테스트")
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
            BookCategoryRepositoryTest.getAnyBookCategory(bookCategoryRepository)
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
    @DisplayName("Book 조회 by id 테스트")
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
            BookCategoryRepositoryTest.getAnyBookCategory(bookCategoryRepository)
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
    @DisplayName("Book 삭제 테스트")
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
            BookCategoryRepositoryTest.getAnyBookCategory(bookCategoryRepository)
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
    @DisplayName("Book 삭제 by id 테스트")
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
            BookCategoryRepositoryTest.getAnyBookCategory(bookCategoryRepository)
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

    @Test
    @DisplayName("Book 조회 by BookCategory 테스트")
    fun test_find_By_Book_Category() {
        // Given
        val bookCategory = BookCategoryRepositoryTest.getAnyBookCategory(bookCategoryRepository)
        val otherBookCategory = BookCategoryRepositoryTest.getOtherBookCategory(bookCategoryRepository, bookCategory)

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
            bookCategory
        )

        val book2 = Book(
            9791196834006,
            "<b>백년운동2</b>",
            19500,
            "http://book.naver.com/bookdb/book_detail.php?bid=15760180",
            "https://bookthumb-phinf.pstatic.net/cover/157/601/15760180.jpg?type=m1&udate=20220212",
            "정선근",
            17550,
            "아티잔",
            "서울대 의대 재활의학과 정선근 교수는 그의 새로운 책, ‘<b>백년운동</b>’을 통해서 100세 인생을 건강하고 멋지게 살고 싶은 이들에게 그 방법을 의학적으로 제시하고 있다. 정 교수가 몇 년 전에 출간한, 건강분야의 스테디셀러인 ‘ 백년허리’ , ‘백년 목’이 허리와 목디스크 질환에 대한 새로운 치료법을... ",
            bookCategory
        )

        val otherBook = Book(
            9791196834007,
            "<b>백년운동3</b>",
            19500,
            "http://book.naver.com/bookdb/book_detail.php?bid=15760180",
            "https://bookthumb-phinf.pstatic.net/cover/157/601/15760180.jpg?type=m1&udate=20220212",
            "정선근",
            17550,
            "아티잔",
            "서울대 의대 재활의학과 정선근 교수는 그의 새로운 책, ‘<b>백년운동</b>’을 통해서 100세 인생을 건강하고 멋지게 살고 싶은 이들에게 그 방법을 의학적으로 제시하고 있다. 정 교수가 몇 년 전에 출간한, 건강분야의 스테디셀러인 ‘ 백년허리’ , ‘백년 목’이 허리와 목디스크 질환에 대한 새로운 치료법을... ",
            otherBookCategory
        )
        bookRepository.save(book)
        bookRepository.save(book2)
        bookRepository.save(otherBook)



        // When
        val findByBookCategory = bookRepository.findByBookCategory(bookCategory, Pageable.unpaged())

        // Then
        assertThat(findByBookCategory)
            .isNotEmpty
            .containsExactly(book, book2)
            .doesNotContain(otherBook)
    }
}