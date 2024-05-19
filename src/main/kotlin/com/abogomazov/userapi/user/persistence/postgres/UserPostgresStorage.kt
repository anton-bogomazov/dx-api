package com.abogomazov.userapi.user.persistence.postgres

import com.abogomazov.userapi.user.domain.User
import com.abogomazov.userapi.user.domain.UserName
import com.abogomazov.userapi.user.usecase.UserExtractor
import com.abogomazov.userapi.user.usecase.UserPersister
import org.springframework.stereotype.Component

@Component
class UserPostgresStorage(
    private val repository: UserRepository,
) : UserExtractor, UserPersister {
    override fun getByName(name: UserName): User? {
        val users = repository.findByFullName(name.fullName.value)
        return when (users.size) {
            0 -> null
            1 -> users.single().toUser()
            else -> error("Invalid db state: ${name.fullName} is not unique")
        }
    }

    override fun getAllUsers(): List<User> {
        return repository.findAll().map { it.toUser() }
    }

    override fun save(user: User) {
        repository.save(UserEntity.from(user))
    }
}