package com.abogomazov.userapi

import com.abogomazov.userapi.user.domain.Name
import io.kotest.assertions.arrow.core.shouldBeRight

fun name() =
    Name(
        firstName = "Joel",
        lastName = "White",
    ).shouldBeRight()