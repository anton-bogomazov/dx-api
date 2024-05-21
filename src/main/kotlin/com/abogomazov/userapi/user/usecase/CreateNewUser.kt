package com.abogomazov.userapi.user.usecase

import arrow.core.Either
import com.abogomazov.userapi.user.domain.User
import com.abogomazov.userapi.user.domain.UserAlreadyExist
import com.abogomazov.userapi.user.domain.UserCreationError
import com.abogomazov.userapi.user.domain.UserId
import com.abogomazov.userapi.user.domain.UserName

class CreateNewUser(
    private val userAlreadyExists: UserAlreadyExist,
    private val persister: UserPersister,
) {
    fun execute(name: UserName): Either<UserCreationError.AlreadyExistsWithTheSameName, UserId> =
        User.createNewUser(
            name = name,
            userAlreadyExists = userAlreadyExists,
        )
            .map { user ->
                persister.save(user)
                user.id
            }
}
