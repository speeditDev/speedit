package com.speedit.server.repository

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.speedit.server.domain.User
import com.speedit.server.domain.enums.Sex
import com.speedit.server.domain.enums.SocialAccountType
import com.speedit.server.repository.jpa.UserRepository
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.transaction.Transactional

@TestPropertySource("classpath:/application.yaml")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional
class UserRepositoryTest(
    @Autowired private val userRepository: UserRepository
) {
    // for pretty print object
    val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    var testUser1: User? = null
    val testUser1SocialAccountId = "KAKAO_2131230980124"

    @BeforeAll
    fun setUp() {
        testUser1 = userRepository.save(User(
            userId = null,
            socialAccountId = testUser1SocialAccountId,
            socialAccountType = SocialAccountType.KAKAO,
            nickName = "Mario",
            thumbnail = null,
            birth = null,
            sex = Sex.M,
            email = null,
            companyName = null,
            companyEmail = null,
            isCompanyEmailValid = false,
            allowedUsedTerm = true,
            allowedPrivacyTerm = false,
            state = "created",
        ))
    }

    @AfterAll
    fun tearDown() {
        userRepository.truncate()
    }

    @Test
    @DisplayName("Either saved testCaseUser find success or not")
    fun testShouldFindTestUser() {
        val foundUser = userRepository.findFirstBySocialAccountId(testUser1SocialAccountId)
        Assertions.assertNotNull(testUser1?.userId)
        Assertions.assertNotNull(foundUser?.userId)
        Assertions.assertEquals(foundUser?.userId, testUser1?.userId)
    }

    @Test
    @DisplayName("Either saved testCaseUser not find success or not")
    fun testShouldNotFindByWrongSocialAccountIdTestUser() {
        val foundUser = userRepository.findFirstBySocialAccountId("GOOGLE_213129837192")
        Assertions.assertNotNull(testUser1?.userId)
        Assertions.assertNull(foundUser?.userId)
        Assertions.assertNotEquals(foundUser?.userId, testUser1?.userId)
    }

    @Test
    @DisplayName("Either save User success or not")
    fun testShouldSaveUserSuccess() {
        val insertUser = User(
            userId = null,
            socialAccountId = "GOOGLE_12302890a2138",
            socialAccountType = SocialAccountType.GOOGLE,
            nickName = "Mario",
            thumbnail = null,
            birth = LocalDate.parse("1991-01-01", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            sex = Sex.M,
            email = "test@book-plate.kr",
            isCompanyEmailValid = false,
            allowedUsedTerm = true,
            allowedPrivacyTerm = true,
            state = "created",
        )
        val savedUser = userRepository.save(insertUser)

        Assertions.assertEquals(savedUser.userId, insertUser.userId)

        println(gson.toJson(insertUser))
        println(gson.toJson(savedUser))
    }

}