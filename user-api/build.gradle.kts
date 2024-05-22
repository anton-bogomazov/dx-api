plugins {
    id(Plugins.kotlin)
    id(Plugins.spring_boot) version Plugins.Versions.spring_boot
    id(Plugins.spring_kotlin) version Plugins.Versions.spring_kotlin
    id(Plugins.jib) version Plugins.Versions.jib
    id(Plugins.test_fixtures)
}

dependencies {
    // COMMON
    implementation(Libs.arrow)

    testImplementation(Libs.spring_boot_starter_test)
    testImplementation(Libs.kotest_arrow)
    testImplementation(Libs.kotest_junit)
    testImplementation(Libs.kotest_assert)
    testImplementation(Libs.kotest_spring)
    testImplementation(Libs.arch_unit)
    testImplementation(Libs.mockk)
    testImplementation(Libs.mockk_spring)
    testImplementation(Libs.kotest_pitest)

    testFixturesImplementation(project(":user-api"))
    testFixturesImplementation(Libs.kotest_arrow)
    testFixturesImplementation(Libs.spring_boot_starter_test)

    // REST
    implementation(Libs.spring_boot_starter_web)
    implementation(Libs.spring_boot_started_hateoas)
    implementation(Libs.jackson_kotlin)
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    // PERSISTENCE
    runtimeOnly(Libs.postgres_driver)
    runtimeOnly(Libs.flyway)
    implementation(Libs.spring_data_jdbc)

    testImplementation(Libs.testcontainers_spring)
    testImplementation(Libs.testcontainers_postgres)
}

val mainClass by extra("com.abogomazov.userapi.UserApiApplicationKt")

jib {
    container {
        ports = listOf("8080")
        mainClass = mainClass
    }
    to {
        image = "abogomazov/user-api"
    }
}
