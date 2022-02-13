package com.speedit.server.repository

import com.speedit.server.domain.User
import com.speedit.server.domain.enums.Sex
import com.speedit.server.respository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import java.time.LocalDate

@TestPropertySource("classpath:/application.yaml")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoryTest {
    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    internal fun createUserTest() {
        val testUser = User(
            userId = null,
            birth = LocalDate.now(),
            thumbnail = null,
            nickName = "test",
            allowedPrivacyTerm = true,
            allowedUsedTerm = true,
            state = 1,
            companyEmail = "testEmail",
            isCompanyEmailValid = false,
            sex = Sex.M,
            email = "test@email.com",
            companyName = "",
            deletedAt = null
        )
        val insertedUser = userRepository.save(testUser)

        Assertions.assertEquals(testUser.birth, insertedUser.birth)
        Assertions.assertEquals(testUser.thumbnail, insertedUser.thumbnail)
        Assertions.assertEquals(testUser.nickName, insertedUser.nickName)
        Assertions.assertEquals(testUser.state, insertedUser.state)
        Assertions.assertEquals(testUser.sex, insertedUser.sex)
        Assertions.assertEquals(testUser.companyEmail, insertedUser.companyEmail)
        Assertions.assertNotNull(insertedUser.createdAt)
        Assertions.assertNotNull(insertedUser.updatedAt)
        Assertions.assertNull(insertedUser.deletedAt)
    }
}