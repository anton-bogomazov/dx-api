package com.abogomazov.userapi.user.usecase

import com.abogomazov.userapi.user.domain.name
import com.abogomazov.userapi.user.domain.userNotExists
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CreateNewUserTest : StringSpec({

    "user is persisted after creation" {
        val storage = UserInMemoryStorage.empty()
        val sut = CreateNewUser(userNotExists, storage)

        val id = sut.execute(name = name()).shouldBeRight()

        storage.getById(id)!!.name shouldBe name()
    }
})
