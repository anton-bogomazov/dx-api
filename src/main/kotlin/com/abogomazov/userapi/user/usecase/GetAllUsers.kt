package com.abogomazov.userapi.user.usecase

class GetAllUsers(
    private val extractor: UserExtractor,
) {
    fun execute() =
        extractor.getAllUsers()
}