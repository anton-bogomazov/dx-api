package com.abogomazov.userapi.user.usecase

import com.abogomazov.userapi.UserInMemoryStorage
import com.abogomazov.userapi.name
import com.abogomazov.userapi.user
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize

class GetAllUsersTest : StringSpec({
    "return empty list from empty storage" {
        val storage = UserInMemoryStorage.empty()
        val sut = GetAllUsers(storage)

        sut.execute().shouldBeEmpty()
    }

    "should return all records from storage" {
        val storage =
            UserInMemoryStorage(
                users =
                    listOf(
                        user(name("Lora", "Palmer")),
                        user(name("Tedd", "Baker")),
                        user(name("Kora", "Wilson")),
                    ),
            )
        val sut = GetAllUsers(storage)

        sut.execute().shouldHaveSize(3)
    }
})
