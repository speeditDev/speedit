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
    var userId: Long? = null,

    @Column(nullable = false)
    var socialAccountId: String,

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "ENUM('KAKAO','GOOGLE','NAVER','APPLE')", nullable = true)
    var socialAccountType: SocialAccountType,

    @Column(length = 50, nullable = false)
    var nickName: String,

    var thumbnail: String? = null,

    @Column
    var birth: LocalDate? = null,

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('M', 'F')")
    var sex: Sex? = null,

    @Column(length = 100)
    var email: String? = null,

    @Column(length = 100)
    var companyName: String? = null,

    @Column(length = 100)
    var companyEmail: String? = null,

    @Column(columnDefinition = "BIT(1)  DEFAULT 0", length = 1, nullable = false)
    var isCompanyEmailValid: Boolean= false,

    @Column(columnDefinition = "BIT(1)  DEFAULT 0", length = 1, nullable = false)
    var allowedUsedTerm: Boolean= false,

    @Column(columnDefinition = "BIT(1) DEFAULT 0", length = 1, nullable = false)
    var allowedPrivacyTerm: Boolean = false,

    @Column(length = 10, nullable = false)
    var state: String = "created",

    @Column(nullable = true)
    var deletedAt: LocalDateTime? = null
) : BaseEntity() {

    @OneToMany(mappedBy = "creator")
    lateinit var feedList: List<Feed>

    @OneToMany(mappedBy = "user")
    lateinit var favoriteBookList: List<FavoriteBook>

    override fun toString(): String {
        return "User(userId=$userId, socialAccountId='$socialAccountId', socialAccountType=$socialAccountType, nickName='$nickName', thumbnail=$thumbnail, birth=$birth, sex=$sex, email='$email', companyName=$companyName, companyEmail=$companyEmail, isCompanyEmailValid=$isCompanyEmailValid, allowedUsedTerm=$allowedUsedTerm, allowedPrivacyTerm=$allowedPrivacyTerm, state=$state, deletedAt=$deletedAt)"
    }
}