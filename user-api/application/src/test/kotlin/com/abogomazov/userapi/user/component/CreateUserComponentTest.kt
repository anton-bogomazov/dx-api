package com.abogomazov.userapi.user.component

import com.abogomazov.userapi.UserApiTestConfiguration
import com.abogomazov.userapi.user.domain.name
import com.abogomazov.userapi.user.usecase.CreateNewUser
import io.kotest.core.spec.style.StringSpec
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [UserApiTestConfiguration::class])
class CreateUserComponentTest(
    private val sut: CreateNewUser,
) : StringSpec({
        "modules are correctly integrated - observe changes" {
            sut.execute(name = name()).isRight()
        }
    })
