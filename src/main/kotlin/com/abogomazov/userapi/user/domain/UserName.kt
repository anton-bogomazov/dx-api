package com.abogomazov.userapi.user.domain

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.zipOrAccumulate
import com.abogomazov.userapi.common.types.ValueObject

data class UserName private constructor(
    val firstName: FirstName,
    val lastName: LastName,
) : ValueObject {
    companion object {
        operator fun invoke(
            firstName: String,
            lastName: String,
        ): Either<NonEmptyList<NameCreationError>, UserName> = either {
            zipOrAccumulate(
                { ensure(firstName.isNotEmpty()) { NameCreationError.FirstNameIsBlank } },
                { ensure(lastName.isNotEmpty()) { NameCreationError.LastNameIsBlank } },
                { ensure(firstName.all { it.isLetter() }) { NameCreationError.FirstNameContainsNonLiterals } },
                { ensure(lastName.all { it.isLetter() }) { NameCreationError.LastNameContainsNonLiterals } },
            ) { _, _, _, _ ->
                UserName(FirstName(firstName), LastName(lastName))
            }
        }
    }

    val fullName: FullName
        get() = FullName("${firstName.value} ${lastName.value}")
}

@JvmInline value class FirstName(val value: String)

@JvmInline value class LastName(val value: String)

@JvmInline value class FullName(val value: String)

sealed interface NameCreationError {
    data object FirstNameIsBlank : NameCreationError
    data object FirstNameContainsNonLiterals : NameCreationError
    data object LastNameIsBlank : NameCreationError
    data object LastNameContainsNonLiterals : NameCreationError
}