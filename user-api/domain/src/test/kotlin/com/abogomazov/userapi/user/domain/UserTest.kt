package com.abogomazov.userapi.user.domain

import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.StringSpec

class UserTest : StringSpec({
    "user is created when its name is unique" {
        User.createNewUser(
            name = name(),
            userAlreadyExists = userNotExists,
        ).shouldBeRight()
    }

    "cannot create user with non-unique name" {
        User.createNewUser(
            name = name(),
            userAlreadyExists = userExists,
        ).shouldBeLeft(UserCreationError.AlreadyExistsWithTheSameName)
    }
})
