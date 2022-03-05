package com.speedit.server.repository.jpa

import com.speedit.server.domain.Feed
import org.springframework.data.jpa.repository.JpaRepository

interface FeedRepository: JpaRepository<Feed, Long> {
}