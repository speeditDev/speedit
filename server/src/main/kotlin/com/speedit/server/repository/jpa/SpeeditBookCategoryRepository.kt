package com.speedit.server.repository.jpa

import com.speedit.server.domain.SpeeditBookCategory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface SpeeditBookCategoryRepository : JpaRepository<SpeeditBookCategory, Long> {
    fun findByName(name: String, pageable: Pageable): Page<SpeeditBookCategory>
    fun findByNameContains(name: String, pageable: Pageable): Page<SpeeditBookCategory>
}