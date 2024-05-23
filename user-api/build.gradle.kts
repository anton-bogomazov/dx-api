plugins {
    com.abogomazov.userapi.convention.`spring-application`
}

dependencies {
    // COMMON
    testImplementation(Libs.spring_boot_starter_test)
    testImplementation(Libs.kotest_arrow)
    testImplementation(Libs.kotest_junit)
    testImplementation(Libs.kotest_assert)
    testImplementation(Libs.kotest_spring)
    testImplementation(Libs.arch_unit)
    testImplementation(Libs.mockk)
    testImplementation(Libs.mockk_spring)
    testImplementation(Libs.kotest_pitest)
    implementation(Libs.spring_boot_starter_logging)
    implementation(Libs.slf4j_api)

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

    // OBSERVABILITY
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.2.5"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("de.codecentric:spring-boot-admin-starter-client:3.2.3")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth:3.1.11")
}

jib {
    container {
        ports = listOf("8080")
        mainClass = "com.abogomazov.userapi.UserApiApplicationKt"
    }
    to {
        image = "abogomazov/user-api"
    }
}
