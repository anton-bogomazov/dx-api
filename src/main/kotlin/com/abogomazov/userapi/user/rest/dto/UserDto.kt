package com.abogomazov.userapi.user.rest.dto

import com.abogomazov.userapi.user.domain.User
import java.util.UUID

data class UserDto(
    val id: UUID,
    val name: UserNameDto,
) {
    data class UserNameDto(
        val firstName: String,
        val lastName: String,
    )

    companion object {
        fun from(user: User) =
            UserDto(
                id = user.id.value,
                name = UserNameDto(
                    firstName = user.name.firstName.value,
                    lastName = user.name.lastName.value,
                ),
            )
    }
}