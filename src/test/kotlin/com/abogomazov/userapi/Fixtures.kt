package com.abogomazov.userapi

import com.abogomazov.userapi.user.domain.UserName
import io.kotest.assertions.arrow.core.shouldBeRight

fun name() =
    UserName(
        firstName = "Joel",
        lastName = "White",
    ).shouldBeRight()