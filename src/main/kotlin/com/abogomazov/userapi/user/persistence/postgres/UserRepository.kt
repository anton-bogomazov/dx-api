package com.abogomazov.userapi.user.persistence.postgres

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface UserRepository : CrudRepository<UserEntity, UUID> {
    @Query("SELECT * FROM users u WHERE concat(u.first_name, ' ', u.last_name) = :fullName")
    fun findByFullName(fullName: String): List<UserEntity>
}
