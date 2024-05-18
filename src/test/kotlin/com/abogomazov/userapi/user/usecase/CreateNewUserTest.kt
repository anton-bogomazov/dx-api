package com.abogomazov.userapi.user.usecase

import com.abogomazov.userapi.UserInMemoryStorage
import com.abogomazov.userapi.name
import com.abogomazov.userapi.user.usecase.invariant.UserAlreadyExistsInvariant
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class CreateNewUserTest : StringSpec({

    "user is persisted after creation" {
        val storage = UserInMemoryStorage.empty()
        val userExists = UserAlreadyExistsInvariant(storage)
        val sut = CreateNewUser(userExists, storage)

        val id = sut.execute(name = name()).shouldBeRight()

        storage.getById(id)!!.name shouldBe name()
    }
})