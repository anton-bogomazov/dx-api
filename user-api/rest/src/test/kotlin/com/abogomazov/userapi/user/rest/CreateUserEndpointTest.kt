package com.abogomazov.userapi.user.rest

import arrow.core.left
import arrow.core.right
import com.abogomazov.userapi.user.domain.UserCreationError
import com.abogomazov.userapi.user.domain.user
import com.abogomazov.userapi.user.usecase.CreateNewUser
import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.StringSpec
import io.mockk.every
import io.mockk.verify
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.web.servlet.function.RequestPredicates.contentType

@WebMvcTest(CreateUserEndpoint::class)
@ContextConfiguration(classes = [CreateUserEndpoint::class, CreateNewUser::class])
class CreateUserEndpointTest(
    private val mockMvc: MockMvc,
    @MockkBean private val usecase: CreateNewUser,
) : StringSpec({

        "user is created from its name" {
            val userId = user().id
            every { usecase.execute(any()) } returns userId.right()

            val input = CreateUserRequest(firstName = "John", lastName = "Doe")
            mockMvc.post(USERS_RESOURCE) {
                content = input.toJsonString()
                contentType = MediaType.APPLICATION_JSON
            }
                .andExpect {
                    status { isCreated() }
                    content { string("") }
                    header { string("Location", userId.uriLocation().toString()) }
                }
            verify { usecase.execute(any()) }
        }

        "cannot create user with non-unique name" {
            every { usecase.execute(any()) } returns UserCreationError.AlreadyExistsWithTheSameName.left()

            val input = CreateUserRequest(firstName = "John", lastName = "Doe")
            mockMvc.post(USERS_RESOURCE) {
                content = input.toJsonString()
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                contentType(MediaType.APPLICATION_PROBLEM_JSON)
                status { isUnprocessableEntity() }
                content {
                    jsonPath("$.type") { value("http://localhost/already_exists") }
                    jsonPath("$.status") { value(HttpStatus.UNPROCESSABLE_ENTITY.value()) }
                }
            }
            verify { usecase.execute(any()) }
        }

        "cannot create user with invalid name" {
            every { usecase.execute(any()) } returns user().id.right()

            val input = CreateUserRequest(firstName = "", lastName = "D0e")
            mockMvc.post(USERS_RESOURCE) {
                content = input.toJsonString()
                contentType = MediaType.APPLICATION_JSON
            }.andExpect {
                contentType(MediaType.APPLICATION_PROBLEM_JSON)
                status { isBadRequest() }
                content {
                    jsonPath("$.type") { value("http://localhost/bad_request") }
                    jsonPath("$.status") { value(HttpStatus.BAD_REQUEST.value()) }
                    jsonPath("$.invalid_params.length()") { value(2) }
                }
            }
        }
    })
