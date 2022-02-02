package com.speedit.server.domain

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false, nullable = false)
    open var createdAt: LocalDateTime = LocalDateTime.now();

    @LastModifiedDate
    @Column(updatable = true, nullable = false)
    open var updatedAt: LocalDateTime = LocalDateTime.now();
}
