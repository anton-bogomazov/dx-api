package com.abogomazov.userapi

import com.abogomazov.userapi.user.domain.UserAlreadyExist
import com.abogomazov.userapi.user.domain.UserName

val userExists = object : UserAlreadyExist {
    override fun invoke(name: UserName) = true
}
val userNotExists = object : UserAlreadyExist {
    override fun invoke(name: UserName) = false
}