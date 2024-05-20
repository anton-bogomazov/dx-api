package com.abogomazov.userapi

import com.abogomazov.userapi.user.domain.User
import com.abogomazov.userapi.user.domain.UserName
import io.kotest.assertions.arrow.core.shouldBeRight

fun name(
    firstName: String = "Joel",
    lastName: String = "White",
) =
    UserName(
        firstName = firstName,
        lastName = lastName,
    ).shouldBeRight()

fun user(
    name: UserName = name(),
) =
    User(
        name = name,
    )