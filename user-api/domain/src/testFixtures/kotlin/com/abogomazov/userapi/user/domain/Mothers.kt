package com.abogomazov.userapi.user.domain

import com.abogomazov.userapi.common.types.Version
import io.kotest.assertions.arrow.core.shouldBeRight

fun name(
    firstName: String = "Joel",
    lastName: String = "White",
) = UserName(
    firstName = firstName,
    lastName = lastName,
).shouldBeRight()

fun user(name: UserName = name()) =
    User(
        id = UserId.next(),
        version = Version.new(),
        name = name,
    )
