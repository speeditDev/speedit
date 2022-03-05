package com.speedit.server.repository.jpa

import com.speedit.server.domain.Book
import com.speedit.server.domain.BookCategory
import com.speedit.server.domain.Sentence
import com.speedit.server.repository.jpa.annotation.DataJpaTestConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime.now

@DataJpaTestConfig
class SentenceRepositoryTest {
    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var bookCategoryRepository: BookCategoryRepository

    @Autowired
    lateinit var sentenceRepository: SentenceRepository

    @Autowired
    lateinit var feedRepository: FeedRepository

    companion object {
        fun createSentence(
            bookRepository: BookRepository,
            feedRepository: FeedRepository
        ): Sentence {
            val feed = FeedRepositoryTest.saveFeed(feedRepository)
            val sentence = Sentence(0L,"test sentence [${now()}]", BookRepositoryTest.getAnyBook(bookRepository))
            feed.addSentence(sentence)

            return sentence
        }

        fun saveSentence(
            sentenceRepository: SentenceRepository,
            bookRepository: BookRepository,
            feedRepository: FeedRepository
        ): Sentence {
            val feed = FeedRepositoryTest.saveFeed(feedRepository)
            val sentence = createSentence(bookRepository, feedRepository)
            feed.addSentence(sentence)
            return sentenceRepository.save(sentence)
        }
    }

    fun saveBookCategory(bookCategoryName: String): BookCategory {
        return BookCategoryRepositoryTest.saveBookCategory(bookCategoryRepository, bookCategoryName)
    }

    fun getAnyBook(): Book {
        return BookRepositoryTest.getAnyBookCategory(bookRepository)
    }

    @BeforeEach
    fun beforeEach() {
        saveBookCategory("book-category-0001")
        saveBookCategory("book-category-0002")
        saveBookCategory("book-category-0003")
        saveBookCategory("book-category-0004")
        saveBookCategory("book-category-0005")

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

        val book2 = Book(
            9791196834006,
            "<b>백년운동2</b>",
            20000,
            "http://book.naver.com/bookdb/book_detail.php?bid=15760180",
            "https://bookthumb-phinf.pstatic.net/cover/157/601/15760180.jpg?type=m1&udate=20220212",
            "정선근",
            17550,
            "아티잔",
            "서울대 의대 재활의학과 정선근 교수는 그의 새로운 책, ‘<b>백년운동</b>’을 통해서 100세 인생을 건강하고 멋지게 살고 싶은 이들에게 그 방법을 의학적으로 제시하고 있다. 정 교수가 몇 년 전에 출간한, 건강분야의 스테디셀러인 ‘ 백년허리’ , ‘백년 목’이 허리와 목디스크 질환에 대한 새로운 치료법을... ",
            BookCategoryRepositoryTest.getOtherBookCategory(bookCategoryRepository, book.bookCategory!!)
        )

        bookRepository.save(book)
        bookRepository.save(book2)
    }

    @Test
    @DisplayName("Sentence 저장 테스트")
    fun test_save_sentence() {
        // Given
        val sentence = createSentence(bookRepository, feedRepository)

        // When
        val savedSentence = sentenceRepository.save(sentence)

        // Then
        val findSentence = sentenceRepository.findById(savedSentence.id)
        assertThat(findSentence)
            .isPresent
            .get()
            .isEqualTo(sentence)

        val relatedFeed = findSentence.get().feed
        assertThat(relatedFeed)
            .isNotNull
        assertThat(feedRepository.findById(relatedFeed.id))
            .isPresent
        assertThat(feedRepository.findById(relatedFeed.id).get().sentenceList)
            .isNotEmpty
            .contains(savedSentence)
    }

    @Test
    @DisplayName("Sentence 수정 테스트")
    fun test_modify_sentence() {
        // Given
        val sentence = saveSentence(sentenceRepository, bookRepository, feedRepository)

        // When
        sentence.sentence = "modifed"
        val modifiedSentence = sentenceRepository.save(sentence)

        // Then
        assertThat(sentenceRepository.findById(modifiedSentence.id))
            .isPresent
            .get()
            .isEqualTo(sentence)
    }

    @Test
    @DisplayName("Sentence 삭제 테스트")
    fun test_delete_sentence_by_id() {
        // Given
        val sentence = saveSentence(sentenceRepository, bookRepository, feedRepository)

        // When
        val deletedId = sentence.id
        sentenceRepository.deleteById(deletedId)

        // Then
        assertThat(sentenceRepository.findById(deletedId))
            .isEmpty
    }

    @Test
    @DisplayName("Sentence 조회 by id 테스트")
    fun test_find_sentence_by_id() {
        // Given
        val sentence = saveSentence(sentenceRepository, bookRepository, feedRepository)

        // When
        val findSentence = sentenceRepository.findById(sentence.id)

        // Then
        assertThat(findSentence)
            .isPresent
            .get()
            .isEqualTo(sentence)
    }

    @Test
    @DisplayName("Sentence 목록 조회 테스트")
    fun test_find_all_sentence_list() {
        // Given
        val sentence1 = saveSentence(sentenceRepository, bookRepository, feedRepository)
        val sentence2 = saveSentence(sentenceRepository, bookRepository, feedRepository)
        val sentence3 = saveSentence(sentenceRepository, bookRepository, feedRepository)

        // When
        val findAllList = sentenceRepository.findAll(Pageable.unpaged())

        // Then
        assertThat(findAllList)
            .isNotEmpty
            .contains(sentence1, sentence2, sentence3)
    }



}