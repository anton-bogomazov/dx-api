package com.abogomazov.userapi.user.postgres

import com.abogomazov.userapi.user.PostgresCleaner
import com.abogomazov.userapi.user.TestContainerConfiguration
import com.abogomazov.userapi.user.domain.user
import com.abogomazov.userapi.user.persistence.postgres.UserApiApplication
import com.abogomazov.userapi.user.persistence.postgres.UserPostgresStorage
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration

@SpringBootTest(classes = [UserApiApplication::class])
@ContextConfiguration(classes = [TestContainerConfiguration::class])
class UserPostgresStorageTest(
    private val sut: UserPostgresStorage,
    private val jdbcTemplate: JdbcTemplate,
) : StringSpec({
        extensions(PostgresCleaner(jdbcTemplate))

        "saved user can be recovered from persistence" {
            val user = user()

            sut.save(user = user)

            sut.getByName(user.name)!!.id shouldBe user.id
        }
    })
