package com.abogomazov.userapi.user.domain

import com.abogomazov.userapi.name
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe

class UserTest : StringSpec({
    "user is created with randomly generated uuid as id" {
        User(name = name()).id shouldNotBe null
    }
})