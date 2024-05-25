package com.abogomazov.userapi

import com.abogomazov.userapi.user.domain.UserAlreadyExist
import com.abogomazov.userapi.user.usecase.CreateNewUser
import com.abogomazov.userapi.user.usecase.GetAllUsers
import com.abogomazov.userapi.user.usecase.UserExtractor
import com.abogomazov.userapi.user.usecase.UserPersister
import com.abogomazov.userapi.user.usecase.invariant.UserAlreadyExistsInvariant
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {
    @Bean
    fun getAllUsers(extractor: UserExtractor) = GetAllUsers(extractor)

    @Bean
    fun createNewUser(
        userAlreadyExists: UserAlreadyExist,
        persister: UserPersister,
    ) = CreateNewUser(userAlreadyExists, persister)

    @Bean
    fun userAlreadyExists(extractor: UserExtractor) = UserAlreadyExistsInvariant(extractor)
}
