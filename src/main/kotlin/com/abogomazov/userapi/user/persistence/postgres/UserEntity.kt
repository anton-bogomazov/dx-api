package com.abogomazov.userapi.user.persistence.postgres

import arrow.core.getOrElse
import com.abogomazov.userapi.user.domain.User
import com.abogomazov.userapi.user.domain.UserId
import com.abogomazov.userapi.user.domain.UserName
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table(name = "users")
class UserEntity private constructor(
    @Id val id: UUID,
    @Version val version: Int = 0,
    val firstName: String,
    val lastName: String,
) {
    companion object {
        fun from(user: User) =
            UserEntity(
                id = user.id.value,
                version = 0,
                firstName = user.name.firstName.value,
                lastName = user.name.lastName.value,
            )
    }

    fun toUser() =
        User(
            id = UserId(id),
            name = UserName(firstName, lastName)
                .getOrElse { err ->
                    error("Invalid db state: UserName is incorrect [$err] for User #$id")
                }
        )
}