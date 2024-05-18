package com.abogomazov.userapi.user.domain

import arrow.core.nel
import arrow.core.toNonEmptyListOrNull
import io.kotest.assertions.arrow.core.shouldBeLeft
import io.kotest.assertions.arrow.core.shouldBeRight
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class NameTest : StringSpec({

    "cannot create name from blank first/last name" {
        Name(firstName = "", lastName = "White")
            .shouldBeLeft(NameValidationError.FirstNameIsBlank.nel())
    }

    "cannot create name from first/last name with spaces" {
        Name(firstName = "Walter White", lastName = "White")
            .shouldBeLeft(NameValidationError.FirstNameContainsNonLiterals.nel())
    }

    "cannot create name from first/last name with digits" {
        Name(firstName = "Walter", lastName = "Wh1te")
            .shouldBeLeft(NameValidationError.LastNameContainsNonLiterals.nel())
    }

    "validation errors is accumulated" {
        Name(firstName = "", lastName = "Wh1te")
            .shouldBeLeft(
                listOf(
                    NameValidationError.FirstNameIsBlank,
                    NameValidationError.LastNameContainsNonLiterals,
                ).toNonEmptyListOrNull()
            )
    }

    "full name is formed using first and last names, separated with space" {
        Name(firstName = "Walter", lastName = "White")
            .shouldBeRight()
            .fullName shouldBe FullName("Walter White")
    }
})