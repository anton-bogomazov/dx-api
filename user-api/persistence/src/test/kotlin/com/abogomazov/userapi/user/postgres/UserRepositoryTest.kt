package com.abogomazov.userapi.user.postgres

import com.abogomazov.userapi.user.AfertestUserCleaner
import com.abogomazov.userapi.user.PersistenceTest
import com.abogomazov.userapi.user.domain.name
import com.abogomazov.userapi.user.domain.user
import com.abogomazov.userapi.user.persistence.postgres.UserEntity
import com.abogomazov.userapi.user.persistence.postgres.UserRepository
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import org.springframework.jdbc.core.JdbcTemplate

class UserRepositoryTest(
    private val sut: UserRepository,
    private val jdbcTemplate: JdbcTemplate,
) : PersistenceTest, StringSpec({
        extensions(AfertestUserCleaner(jdbcTemplate))

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
