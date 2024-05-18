package com.abogomazov.userapi.user.rest

import com.abogomazov.userapi.toType
import com.abogomazov.userapi.user
import com.abogomazov.userapi.user.rest.dto.UserDto
import com.abogomazov.userapi.user.usecase.GetAllUsers
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.mockk.every
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest(GetAllUsersEndpoint::class)
class GetAllUsersEndpointTest(
    private val mockMvc: MockMvc,
    @MockkBean private val usecase: GetAllUsers,
) : StringSpec({

    "all persisted users returned as dtos" {
        every { usecase.execute() } returns listOf(user(), user())

        val result = mockMvc.get("/api/v1/users")
            .andExpect { status { isOk() } }
            .andReturn()
            .toType<List<UserDto>>()

        result.shouldHaveSize(2)
    }
})