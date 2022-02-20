package com.speedit.server.domain

import com.speedit.server.domain.enums.Sex
import com.speedit.server.domain.enums.SocialAccountType
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "User")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Long?,

    @Column(nullable = false)
    val socialAccountId: String,

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "ENUM('KAKAO','GOOGLE','NAVER','APPLE')", nullable = true)
    var socialAccountType: SocialAccountType?,

    @Column(length = 50, nullable = false)
    var nickName: String,

    var thumbnail: String?,

    @Column(nullable = false)
    var birth: LocalDate,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('M', 'F') DEFAULT 'M'")
    var sex: Sex,

    @Column(length = 100, nullable = false)
    var email: String,

    @Column(length = 100)
    var companyName: String?,

    @Column(length = 100)
    var companyEmail: String?,

    @Column(columnDefinition = "BIT(1)  DEFAULT 0", length = 1, nullable = false)
    var isCompanyEmailValid: Boolean,

    @Column(columnDefinition = "BIT(1)  DEFAULT 0", length = 1, nullable = false)
    var allowedUsedTerm: Boolean,

    @Column(columnDefinition = "BIT(1) DEFAULT 0", length = 1, nullable = false)
    var allowedPrivacyTerm: Boolean,

    @Column(length = 10, nullable = false)
    var state: String,

    @Column(nullable = true)
    var deletedAt: LocalDateTime? = null
) : BaseEntity() {
    override fun toString(): String {
        return "User(userId=$userId, socialAccountId='$socialAccountId', socialAccountType=$socialAccountType, nickName='$nickName', thumbnail=$thumbnail, birth=$birth, sex=$sex, email='$email', companyName=$companyName, companyEmail=$companyEmail, isCompanyEmailValid=$isCompanyEmailValid, allowedUsedTerm=$allowedUsedTerm, allowedPrivacyTerm=$allowedPrivacyTerm, state=$state, deletedAt=$deletedAt)"
    }
}