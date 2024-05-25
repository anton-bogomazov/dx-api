package com.abogomazov.userapi.user.postgres

import com.abogomazov.userapi.user.AfertestUserCleaner
import com.abogomazov.userapi.user.PersistenceTest
import com.abogomazov.userapi.user.domain.user
import com.abogomazov.userapi.user.persistence.postgres.UserPostgresStorage
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.jdbc.core.JdbcTemplate

class UserPostgresStorageTest(
    private val sut: UserPostgresStorage,
    private val jdbcTemplate: JdbcTemplate,
) : PersistenceTest, StringSpec({
        extensions(AfertestUserCleaner(jdbcTemplate))

        "saved user can be recovered from persistence" {
            val user = user()

            sut.save(user = user)

            sut.getByName(user.name)!!.id shouldBe user.id
        }
    })
