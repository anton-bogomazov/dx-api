package com.abogomazov.userapi.user.domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.abogomazov.userapi.common.DomainError

class User(
    val id: UserId = UserId.next(),
    val name: UserName,
) {
    companion object {
        fun createNewUser(
            name: UserName,
            userAlreadyExists: UserAlreadyExist,
        ): Either<UserCreationError.AlreadyExistsWithTheSameName, User> = either {
            ensure(!userAlreadyExists(name)) { UserCreationError.AlreadyExistsWithTheSameName }

            User(name = name)
        }
    }
}

sealed interface UserCreationError : DomainError {
    data object AlreadyExistsWithTheSameName : UserCreationError
}

interface UserAlreadyExist {
    operator fun invoke(name: UserName): Boolean
}