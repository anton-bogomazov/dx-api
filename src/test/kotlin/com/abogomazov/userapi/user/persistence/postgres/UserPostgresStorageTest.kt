package com.abogomazov.userapi.user.persistence.postgres

import com.abogomazov.userapi.TestContainerConfiguration
import com.abogomazov.userapi.user
import com.abogomazov.userapi.user.PostgresCleaner
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration

@SpringBootTest
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