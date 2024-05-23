package com.abogomazov.userapi.user.domain

val userExists =
    object : UserAlreadyExist {
        override fun invoke(name: UserName) = true
    }
val userNotExists =
    object : UserAlreadyExist {
        override fun invoke(name: UserName) = false
    }
