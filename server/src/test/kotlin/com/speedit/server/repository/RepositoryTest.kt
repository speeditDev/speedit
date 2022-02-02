package com.speedit.server.repository

import com.speedit.server.domain.User
import com.speedit.server.domain.enums.Sex
import com.speedit.server.respository.UserRepository
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
            sex = Sex.M
        )
        val insertedUser = userRepository.save(testUser)
        println(insertedUser)
        println(insertedUser.updatedAt)
        println(insertedUser.createdAt)
//        TODO("Not yet implemented")
    }
}