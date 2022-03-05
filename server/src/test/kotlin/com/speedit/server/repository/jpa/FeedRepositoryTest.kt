package com.speedit.server.repository.jpa

import com.speedit.server.domain.Feed
import com.speedit.server.repository.jpa.annotation.DataJpaTestConfig
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable

@DataJpaTestConfig
class FeedRepositoryTest {
    @Autowired
    lateinit var feedRepository: FeedRepository

    companion object {
        fun createFeed(): Feed {
            return Feed(0L)
        }

        fun saveFeed(feedRepository: FeedRepository): Feed {
            return feedRepository.save(createFeed())
        }
    }


    @Test
    @DisplayName("Feed 저장 테스트")
    fun test_save_feed() {
        // Given
        val feed = createFeed()

        // When
        val savedFeed = feedRepository.save(feed)

        // Then
        assertThat(feedRepository.findById(savedFeed.id))
            .isPresent
            .get()
            .isEqualTo(feed)
    }

    @Test
    @DisplayName("Feed 수정 테스트")
    fun test_modify_feed() {

        // Given
        val feed = saveFeed(feedRepository)

        // When
        val modifiedFeed = feedRepository.save(feed)
        // TODO: User, Sentence Modify



        // Then
        assertThat(feedRepository.findById(modifiedFeed.id))
            .isPresent
            .get()
            .isEqualTo(feed)
    }

    @Test
    @DisplayName("Feed 삭제 테스트")
    fun test_delete_feed_by_id() {
        // Given
        val feed = saveFeed(feedRepository)

        // When
        val deletedId = feed.id
        feedRepository.deleteById(deletedId)

        // Then
        assertThat(feedRepository.findById(deletedId))
            .isEmpty
    }

    @Test
    @DisplayName("Feed 조회 by id 테스트")
    fun test_find_feed_by_id() {
        // Given
        val feed = saveFeed(feedRepository)

        // When
        val findFeed = feedRepository.findById(feed.id)

        // Then
        assertThat(findFeed)
            .isPresent
            .get()
            .isEqualTo(feed)
    }

    @Test
    @DisplayName("Feed 목록 조회 테스트")
    fun test_find_all_feed_list() {
        // Given
        val feed1 = saveFeed(feedRepository)
        val feed2 = saveFeed(feedRepository)
        val feed3 = saveFeed(feedRepository)

        // When
        val findAllList = feedRepository.findAll(Pageable.unpaged())

        // Then
        assertThat(findAllList)
            .isNotEmpty
            .contains(feed1, feed2, feed3)
    }
}