package com.abogomazov.userapi.user.usecase

import com.abogomazov.userapi.user.domain.User
import com.abogomazov.userapi.user.domain.UserName

interface UserPersister {
    fun save(user: User)
}

interface UserExtractor {
    fun getByName(name: UserName): User?
}