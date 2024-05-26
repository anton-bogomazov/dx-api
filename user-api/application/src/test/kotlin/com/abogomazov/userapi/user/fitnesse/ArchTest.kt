package com.abogomazov.userapi.user.fitnesse

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests
import com.tngtech.archunit.library.Architectures.onionArchitecture
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices
import io.kotest.core.spec.style.StringSpec

class ArchTest : StringSpec({

    val classes =
        ClassFileImporter()
            .withImportOption(DoNotIncludeTests())
            .withImportOption(DoNotImportMetrics)
            .importPackages("com.abogomazov.userapi.user")

    "application follows onion arch rules" {
        onionArchitecture()
            .domainModels("com.abogomazov.userapi.user.domain..")
            .domainServices("com.abogomazov.userapi.user.domain..")
            .applicationServices("com.abogomazov.userapi.user.usecase..")
            .adapter("Postgres Persistence", "..persistence.postgres..")
            .adapter("Rest API", "..rest..")
            .check(classes)
    }

    "free of cycle dependencies" {
        slices().matching("com.abogomazov.userapi.(**)")
            .should().beFreeOfCycles()
    }
})
