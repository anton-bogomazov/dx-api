package com.abogomazov.userapi.user.usecase.invariant

import com.abogomazov.userapi.user.domain.UserAlreadyExist
import com.abogomazov.userapi.user.domain.UserName
import com.abogomazov.userapi.user.usecase.UserExtractor

class UserAlreadyExistsInvariant(
    private val extractor: UserExtractor,
) : UserAlreadyExist {
    override fun invoke(name: UserName): Boolean =
        extractor.getByName(name) != null
}