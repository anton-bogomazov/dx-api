package com.abogomazov.userapi.user.rest

import arrow.core.Either
import arrow.core.NonEmptyList
import com.abogomazov.userapi.user.domain.*
import com.abogomazov.userapi.user.usecase.CreateNewUser
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping(USERS_RESOURCE)
class CreateUserEndpoint(
    private val usecase: CreateNewUser,
) {

    @PostMapping
    fun handle(@RequestBody request: CreateUserRequest): ResponseEntity<*> =
        UserName.validated(request.firstName, request.lastName).fold(
            { it.toInvalidParamsBadRequest() },
            { name ->
                usecase.execute(name).fold(
                    { it.toRestError() },
                    { created(it.uriLocation()) },
                )
            }
        )
}

fun UserId.uriLocation() =
    URI("$USERS_RESOURCE/${value}")


fun UserCreationError.toRestError() =
    when (this) {
        is UserCreationError.AlreadyExistsWithTheSameName,
        -> restBusinessError("User already exists", "already_exists")
    }

data class CreateUserRequest(
    val firstName: String,
    val lastName: String,
)

fun UserName.Companion.validated(firstName: String, lastName: String): Either<NonEmptyList<ValidationError>, UserName> =
    UserName(firstName, lastName).mapLeft {
        it.map {
            when (it) {
                is NameCreationError.FirstNameIsBlank,
                is NameCreationError.LastNameIsBlank,
                -> ValidationError(message = "Name is empty")
                is NameCreationError.FirstNameContainsNonLiterals,
                is NameCreationError.LastNameContainsNonLiterals,
                -> ValidationError(message = "Name contains non-literal characters")
            }
        }
    }