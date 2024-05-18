package com.abogomazov.userapi.user.domain

import java.util.UUID

@JvmInline value class UserId(val value: UUID) {
    companion object {
        fun next() = UserId(UUID.randomUUID())
    }
}