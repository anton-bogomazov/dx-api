package com.abogomazov.userapi

sealed interface DomainError

sealed interface ValidationError : DomainError