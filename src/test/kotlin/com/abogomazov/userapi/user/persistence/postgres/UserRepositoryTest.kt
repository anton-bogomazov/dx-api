package com.abogomazov.userapi.user.persistence.postgres

import com.abogomazov.userapi.TestContainerConfiguration
import com.abogomazov.userapi.name
import com.abogomazov.userapi.user
import com.abogomazov.userapi.user.PostgresCleaner
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
@ContextConfiguration(classes = [TestContainerConfiguration::class])
class UserRepositoryTest(
    private val sut: UserRepository,
    private val jdbcTemplate: JdbcTemplate,
) : StringSpec({
    extensions(PostgresCleaner(jdbcTemplate))

    val user = user(name(firstName = "Joel", lastName = "White"))

    "return single user when full name matches" {
        sut.save(UserEntity.from(user))

        sut.findByFullName("Joel White")
            .shouldHaveSize(1)
    }

    "do not return anything when matches partly" {
        sut.save(UserEntity.from(user))

        sut.findByFullName("Joel Woe")
            .shouldBeEmpty()
    }

    "do not return anything when input contains extra spaces" {
        sut.save(UserEntity.from(user))

        sut.findByFullName("Joel  White")
            .shouldBeEmpty()
    }
})