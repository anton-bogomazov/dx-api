package com.abogomazov.userapi.user.rest.dto

import com.abogomazov.userapi.user.domain.User
import com.abogomazov.userapi.user.domain.UserName
import java.util.UUID

data class UserDto(
    val id: UUID,
    val name: UserNameDto,
) {
    companion object {
        fun from(user: User) =
            UserDto(
                id = user.id.value,
                name = user.name.let(UserNameDto::from),
            )
    }
}

data class UserNameDto(
    val firstName: String,
    val lastName: String,
) {
    companion object {
        fun from(name: UserName) =
            UserNameDto(
                firstName = name.firstName.value,
                lastName = name.lastName.value,
            )
    }
}
