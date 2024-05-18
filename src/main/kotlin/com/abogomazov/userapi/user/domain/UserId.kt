package com.abogomazov.userapi.user.domain

import java.util.UUID

@JvmInline value class UserId private constructor(val value: UUID) {
    companion object {
        fun next() = UserId(UUID.randomUUID())
    }
}