package com.abogomazov.userapi.user

import com.tngtech.archunit.core.importer.ClassFileImporter
import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests
import com.tngtech.archunit.library.Architectures.onionArchitecture
import io.kotest.core.spec.style.StringSpec

class ArchTest : StringSpec({
    "application follows onion arch rules" {
        val jc = ClassFileImporter()
            .withImportOption(DoNotIncludeTests())
            .importPackages("com.abogomazov.userapi.user")

        val definition = onionArchitecture()
            .domainModels("..domain..")
            .applicationServices("..usecase..")
            .adapter("Postgres Persistence", "..persistence.postgres..")
            .adapter("Rest API", "..rest..")
            .withOptionalLayers(true)

        definition.check(jc)
    }
})