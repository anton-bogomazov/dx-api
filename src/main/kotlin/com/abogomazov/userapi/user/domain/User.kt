package com.abogomazov.userapi.user.domain

class User private constructor(
    val id: UserId = UserId.next(),
    val name: UserName,
)