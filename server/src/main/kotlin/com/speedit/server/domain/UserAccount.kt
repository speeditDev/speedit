package com.speedit.server.domain

import com.speedit.server.domain.enums.SocialAccountType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class UserAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userAccountId")
    var userAccountId: Long?,

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "ENUM('KAKAO','GOOGLE','NAVER','APPLE')", nullable = true)
    var socialAccountType: SocialAccountType?,

    @Column(length = 100, nullable = false)
    val email: String,

    @Column(columnDefinition= "bit DEFAULT 1", nullable = false)
    val state: Int,

    @Column(nullable = false)
    val socialAccountId: String,

    @OneToOne(optional = false)
    @JoinColumn(name = "userId")
    val User: User?,

    @Column(nullable = true)
    val deletedAt: LocalDateTime? = null
): BaseEntity()
