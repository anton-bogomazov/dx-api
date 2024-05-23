package com.abogomazov.userapi.user.usecase

import com.abogomazov.userapi.user.domain.User
import com.abogomazov.userapi.user.domain.UserId
import com.abogomazov.userapi.user.domain.UserName

class UserInMemoryStorage(
    users: List<User> = emptyList(),
) : UserExtractor, UserPersister {
    companion object {
        fun empty() = UserInMemoryStorage()
    }

    private val storage: MutableList<User> =
        users.toMutableList()

    override fun getByName(name: UserName): User? = storage.find { it.name == name }

    override fun getAllUsers(): List<User> {
        return storage
    }

    fun getById(id: UserId): User? = storage.find { it.id == id }

    override fun save(user: User) {
        storage.addLast(user)
    }
}
