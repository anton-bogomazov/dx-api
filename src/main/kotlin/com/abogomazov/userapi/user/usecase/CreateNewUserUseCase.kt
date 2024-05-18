package com.abogomazov.userapi.user.usecase

import arrow.core.Either
import com.abogomazov.userapi.user.domain.*

class CreateNewUserUseCase(
    private val userAlreadyExists: UserAlreadyExist,
    private val persister: UserPersister,
) {
    fun execute(
        name: UserName,
    ): Either<AlreadyExistsWithTheSameName, UserId> =
        User.createNewUser(
            name = name,
            userAlreadyExists = userAlreadyExists,
        )
        .map { user ->
            persister.save(user)
            user.id
        }
}