package com.abogomazov.userapi.user.domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.abogomazov.userapi.DomainError

class User(
    val id: UserId = UserId.next(),
    val name: UserName,
) {
    companion object {
        fun createNewUser(
            name: UserName,
            userAlreadyExists: UserAlreadyExist,
        ): Either<AlreadyExistsWithTheSameName, User> = either {
            ensure(!userAlreadyExists(name)) { AlreadyExistsWithTheSameName }

            User(name = name)
        }
    }
}

data object AlreadyExistsWithTheSameName : DomainError

interface UserAlreadyExist {
    operator fun invoke(name: UserName): Boolean
}