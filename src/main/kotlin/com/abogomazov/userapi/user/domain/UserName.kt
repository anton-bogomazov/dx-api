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
        ): Either<NonEmptyList<NameCreationError>, UserName> =
            either {
                zipOrAccumulate(
                    { FirstName.from(firstName).bindNel() },
                    { LastName.from(lastName).bindNel() },
                ) { first, last ->
                    UserName(first, last)
                }
            }
    }

    val fullName: FullName
        get() = FullName("${firstName.value} ${lastName.value}")
}

@JvmInline value class FirstName(val value: String) {
    companion object {
        fun from(value: String): Either<NonEmptyList<NameCreationError>, FirstName> =
            either {
                zipOrAccumulate(
                    { ensure(value.isNotEmpty()) { NameCreationError.FirstNameIsBlank } },
                    { ensure(value.all { it.isLetter() }) { NameCreationError.FirstNameContainsNonLiterals } },
                ) { _, _ -> FirstName(value) }
            }
    }
}

@JvmInline value class LastName(val value: String) {
    companion object {
        fun from(value: String): Either<NonEmptyList<NameCreationError>, LastName> =
            either {
                zipOrAccumulate(
                    { ensure(value.isNotEmpty()) { NameCreationError.LastNameIsBlank } },
                    { ensure(value.all { it.isLetter() }) { NameCreationError.LastNameContainsNonLiterals } },
                ) { _, _ -> LastName(value) }
            }
    }
}

@JvmInline value class FullName(val value: String)

sealed interface NameCreationError {
    data object FirstNameIsBlank : NameCreationError

    data object FirstNameContainsNonLiterals : NameCreationError

    data object LastNameIsBlank : NameCreationError

    data object LastNameContainsNonLiterals : NameCreationError
}
