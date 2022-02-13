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
    var userId: Long?,

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

    @Column(columnDefinition= "TINYINT", length = 1, nullable = false)
    var allowedUsedTerm: Boolean,

    @Column(columnDefinition= "TINYINT", length = 1, nullable = false)
    var allowedPrivacyTerm: Boolean,

    @Column(columnDefinition= "TINYINT", length = 1, nullable = false)
    var isCompanyEmailValid: Boolean,

    @Column(columnDefinition= "TINYINT", length = 1, nullable = false)
    var state: Int,

    @Column(nullable = true)
    var deletedAt: LocalDateTime? = null
): BaseEntity() {
    override fun toString(): String {
        return "User(userId=$userId, nickName='$nickName', thumbnail=$thumbnail, birth=$birth, sex=$sex, \n" +
                " allowedUsedTerm=$allowedUsedTerm, allowedPrivacyTerm=$allowedPrivacyTerm," +
                " email='$email' companyEmail='$companyEmail', isCompanyEmailValid=$isCompanyEmailValid, \n" +
                " state=$state, createdAt= $createdAt, updatedAt= $updatedAt deletedAt=$deletedAt)"
    }
}