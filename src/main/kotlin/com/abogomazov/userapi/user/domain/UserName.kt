package com.abogomazov.userapi.user.domain

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.raise.zipOrAccumulate
import com.abogomazov.userapi.ValidationError

data class UserName private constructor(
    val firstName: FirstName,
    val lastName: LastName,
) {
    companion object {
        operator fun invoke(
            firstName: String,
            lastName: String,
        ): Either<NonEmptyList<NameValidationError>, UserName> = either {
            zipOrAccumulate(
                { ensure(firstName.isNotEmpty()) { NameValidationError.FirstNameIsBlank } },
                { ensure(lastName.isNotEmpty()) { NameValidationError.LastNameIsBlank } },
                { ensure(firstName.all { it.isLetter() }) { NameValidationError.FirstNameContainsNonLiterals } },
                { ensure(lastName.all { it.isLetter() }) { NameValidationError.LastNameContainsNonLiterals } },
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

sealed interface NameValidationError : ValidationError {
    data object FirstNameIsBlank : NameValidationError
    data object FirstNameContainsNonLiterals : NameValidationError
    data object LastNameIsBlank : NameValidationError
    data object LastNameContainsNonLiterals : NameValidationError
}