package com.abogomazov.userapi.user.domain

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.abogomazov.userapi.common.types.AggregateRoot
import com.abogomazov.userapi.common.types.DomainError
import com.abogomazov.userapi.common.types.DomainEvent
import com.abogomazov.userapi.common.types.Version

class User(
    id: UserId,
    val name: UserName,
    version: Version,
) : AggregateRoot<UserId>(id, version) {

    companion object {
        fun createNewUser(
            name: UserName,
            userAlreadyExists: UserAlreadyExist,
        ): Either<UserCreationError.AlreadyExistsWithTheSameName, User> = either {
            ensure(!userAlreadyExists(name)) { UserCreationError.AlreadyExistsWithTheSameName }

            User(
                id = UserId.next(),
                name = name,
                version = Version.new(),
            ).apply {
                addEvent(UserCreated(id))
            }
        }
    }
}

data class UserCreated(val userId: UserId) : DomainEvent()

sealed interface UserCreationError : DomainError {
    data object AlreadyExistsWithTheSameName : UserCreationError
}

interface UserAlreadyExist {
    operator fun invoke(name: UserName): Boolean
}