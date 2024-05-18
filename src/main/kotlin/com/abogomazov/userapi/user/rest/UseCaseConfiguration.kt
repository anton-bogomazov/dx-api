package com.abogomazov.userapi.user.rest

import com.abogomazov.userapi.user.usecase.GetAllUsers
import com.abogomazov.userapi.user.usecase.UserExtractor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UseCaseConfiguration {
    @Bean
    fun getAllUsers(extractor: UserExtractor) =
        GetAllUsers(extractor)
}