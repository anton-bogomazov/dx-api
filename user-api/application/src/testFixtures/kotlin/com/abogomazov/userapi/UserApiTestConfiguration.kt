package com.abogomazov.userapi

import com.abogomazov.userapi.user.usecase.UserExtractor
import com.abogomazov.userapi.user.usecase.UserInMemoryStorage
import com.abogomazov.userapi.user.usecase.UserPersister
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(UseCaseConfiguration::class)
class UserApiTestConfiguration {
    // Fake infrastructure dependencies

    @Bean
    fun storage() = UserInMemoryStorage()

    @Bean
    fun persister(storage: UserInMemoryStorage): UserPersister = storage

    @Bean
    fun extractor(storage: UserInMemoryStorage): UserExtractor = storage
}
