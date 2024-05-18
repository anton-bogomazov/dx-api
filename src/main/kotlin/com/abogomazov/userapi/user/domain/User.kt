package com.abogomazov.userapi.user.domain

import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val name: Name,
)