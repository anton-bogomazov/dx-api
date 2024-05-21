package com.abogomazov.userapi.user.rest

import com.abogomazov.userapi.user.rest.dto.UserDto
import com.abogomazov.userapi.user.usecase.GetAllUsers
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(USERS_RESOURCE)
class GetAllUsersEndpoint(
    private val usecase: GetAllUsers,
) {
    @GetMapping
    fun handle(): List<UserDto> =
        usecase.execute()
            .map(UserDto::from)
}
