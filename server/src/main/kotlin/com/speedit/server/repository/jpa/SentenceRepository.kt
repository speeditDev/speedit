package com.speedit.server.repository.jpa

import com.speedit.server.domain.Sentence
import org.springframework.data.jpa.repository.JpaRepository

interface SentenceRepository: JpaRepository<Sentence, Long> {
}