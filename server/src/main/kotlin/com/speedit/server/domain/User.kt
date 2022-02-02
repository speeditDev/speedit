package com.speedit.server.domain

import com.speedit.server.domain.enums.Sex
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "User")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    var userId: Long?,

    @Column(length = 50, nullable = false)
    val nickName: String,

    val thumbnail: String?,

    @Column(nullable = false)
    val birth: LocalDate,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('M', 'F')")
    val sex: Sex,

    @Column(nullable = false)
    val allowedUsedTerm: Boolean,

    @Column(nullable = false)
    val allowedPrivacyTerm: Boolean,

    @Column(length = 100, nullable = false)
    val companyEmail: String,

    @Column(nullable = false)
    val isCompanyEmailValid: Boolean,

    @Column(columnDefinition= "bit DEFAULT 1", nullable = false)
    val state: Int,

    @Column(nullable = true)
    var deletedAt: LocalDateTime? = null
): BaseEntity() {
    override fun toString(): String {
        return "User(userId=$userId, nickName='$nickName', thumbnail=$thumbnail, birth=$birth, sex=$sex, \n" +
                " allowedUsedTerm=$allowedUsedTerm, allowedPrivacyTerm=$allowedPrivacyTerm, companyEmail='$companyEmail', isCompanyEmailValid=$isCompanyEmailValid, \n" +
                " state=$state, createdAt= $createdAt, updatedAt= $updatedAt deletedAt=$deletedAt)"
    }
}