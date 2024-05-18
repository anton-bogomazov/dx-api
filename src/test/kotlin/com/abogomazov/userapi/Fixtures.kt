package com.abogomazov.userapi

import com.abogomazov.userapi.user.domain.UserAlreadyExist
import com.abogomazov.userapi.user.domain.UserName
import io.kotest.assertions.arrow.core.shouldBeRight

// MOTHERS
fun name() =
    UserName(
        firstName = "Joel",
        lastName = "White",
    ).shouldBeRight()

// FAKES
val userExists = object : UserAlreadyExist {
    override fun invoke(name: UserName) = true
}
val userNotExists = object : UserAlreadyExist {
    override fun invoke(name: UserName) = false
}