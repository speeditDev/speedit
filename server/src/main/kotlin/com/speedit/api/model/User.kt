package com.speedit.api.model

import org.springframework.data.annotation.Id
import java.time.LocalDateTime

data class User(@Id var idx: Int,
                val email: String,
                var displayName: String,
                var createdAt: LocalDateTime,
                var updatedAt: LocalDateTime
) {
}
