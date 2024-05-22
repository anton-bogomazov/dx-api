object Libs {
    object Versions {
        const val spring_boot = "3.2.5"
        const val swagger = "3.0.0"
        const val junit = "5.7.1"
        const val kotest = "5.9.0"
        const val kotest_arrow = "1.4.0"
        const val jackson = "2.17.1"
        const val arrow = "1.2.4"
        const val slf4j = "1.7.32"
        const val ascii_table = "1.2.0"
        const val postgresql = "42.7.3"
        const val flyway = "10.13.0"
        const val testng = "7.3.0"
        const val arch_unit = "1.3.0"
        const val faker = "2.2.2"
        const val testcontainers = "1.19.8"
        const val pact = "4.3.2"
        const val wiremock = "2.31.0"
        const val resilience4j_circuitbreaker = "1.7.1"
        const val resilience4j_bulkhead = "1.7.1"
        const val kbdd = "1.1.1"
        const val koin = "3.5.0"
        const val rest_assured = "5.4.0"
        const val corounit = "1.1.1"
        const val spring_rabbit_test = "2.4.0"
        const val hal_explorer = "1.0.1"
        const val mockk = "1.13.11"
        const val mockk_spring = "4.0.2"
        const val kotest_spring = "1.1.3"
    }
    // Kotlin
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Global.kotlin_version}"
    const val kotlin_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Global.kotlin_version}"
    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Global.kotlin_version}"
    const val arrow = "io.arrow-kt:arrow-core:${Versions.arrow}"

    // Jackson
    const val jackson_kotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}"
    const val jackson_databind = "com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}"

    // Spring
    const val spring_boot_starter_web = "org.springframework.boot:spring-boot-starter-web:${Versions.spring_boot}"
    const val spring_data_jdbc =
        "org.springframework.boot:spring-boot-starter-data-jdbc:${Versions.spring_boot}"
    const val spring_boot_starter_logging = "org.springframework.boot:spring-boot-starter-logging:${
        Versions.spring_boot
    }"
    const val spring_boot_starter_test = "org.springframework.boot:spring-boot-starter-test:${Versions.spring_boot}"
    const val spring_boot_starter_thymeleaf =
        "org.springframework.boot:spring-boot-starter-thymeleaf:${Versions.spring_boot}"

    const val spring_boot_started_hateoas =
        "org.springframework.boot:spring-boot-starter-hateoas:${Versions.spring_boot}"

    const val spring_boot_starter_amqp =
        "org.springframework.boot:spring-boot-starter-amqp:${Versions.spring_boot}"

    const val swagger = "io.springfox:springfox-boot-starter:${Versions.swagger}"
    const val swagger_ui = "io.springfox:springfox-swagger-ui:${Versions.swagger}"

    // Logging
    const val slf4j_api = "org.slf4j:slf4j-api:${Versions.slf4j}"

    // Table
    const val ascii_table = "com.github.freva:ascii-table:${Versions.ascii_table}"

    // Tests
    const val junit_params = "org.junit.jupiter:junit-jupiter-params:${Versions.junit}"
    const val junit_engine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junit}"
    const val kotest_junit = "io.kotest:kotest-runner-junit5-jvm:${Versions.kotest}"
    const val kotest_assert = "io.kotest:kotest-assertions-core-jvm:${Versions.kotest}"
    const val kotest_spring = "io.kotest.extensions:kotest-extensions-spring:${Versions.kotest_spring}"
    const val kotest_arrow = "io.kotest.extensions:kotest-assertions-arrow:${Versions.kotest_arrow}"
    const val arch_unit = "com.tngtech.archunit:archunit:${Versions.arch_unit}"
    const val mockk_spring = "com.ninja-squad:springmockk:${Versions.mockk_spring}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"

    const val faker = "net.datafaker:datafaker:${Versions.faker}"
    const val testcontainers_postgres = "org.testcontainers:postgresql:${Versions.testcontainers}"
    const val testcontainers_rabbit = "org.testcontainers:rabbitmq:${Versions.testcontainers}"
    const val testcontainers_core = "org.testcontainers:testcontainers:${Versions.testcontainers}"
    const val testcontainers_spring = "org.springframework.boot:spring-boot-testcontainers:${Versions.spring_boot}"
    const val wiremock = "com.github.tomakehurst:wiremock-jre8:${Versions.wiremock}"
    const val kbdd = "ru.fix:kbdd:${Versions.kbdd}"
    const val koin = "io.insert-koin:koin-core:${Versions.koin}"
    const val rest_assured = "io.rest-assured:rest-assured:${Versions.rest_assured}"
    const val rest_assured_kotlin = "io.rest-assured:kotlin-extensions:${Versions.rest_assured}"
    const val spring_rabbit_test = "org.springframework.amqp:spring-rabbit-test:${Versions.spring_rabbit_test}"

    const val jfix_corounit_engine = "ru.fix:corounit-engine:${Versions.corounit}"
    const val jfix_corounit_allure = "ru.fix:corounit-allure:${Versions.corounit}"

    // Database
    const val postgres_driver = "org.postgresql:postgresql:${Versions.postgresql}"
    const val flyway = ("org.flywaydb:flyway-database-postgresql:${Versions.flyway}")

    // REST
    const val hal_explorer = "org.webjars:hal-explorer:${Versions.hal_explorer}"

    // resilience4j
    const val resilience4j_circuitbreaker = "io.github.resilience4j:resilience4j-circuitbreaker:" +
            Versions.resilience4j_circuitbreaker
    const val resilience4j_bulkhead = "io.github.resilience4j:resilience4j-bulkhead:" +
            Versions.resilience4j_bulkhead

}

object Plugins {
    object Versions {
        const val kotlin = Global.kotlin_version
        const val spring_boot = Libs.Versions.spring_boot
        const val detekt = "1.23.6"
        const val detekt_formatting = detekt
        const val spring_dependency_management = "1.0.11.RELEASE"
        const val spring_kotlin = Global.kotlin_version
        const val update_dependencies = "0.51.0"
        const val owasp_dependencies = "6.1.0"
        const val pitest = "1.7.0"
        const val allure = "2.11.2"
        const val allure_cli = "2.29.0"
        const val allure_java = "2.27.0"
        const val gatling = "3.11.2"
        const val jib = "3.4.2"
        const val ktlint = "12.1.1"
    }

    const val kotlin = "com.abogomazov.userapi.convention.kotlin-jvm"
//    const val kotlin = "org.jetbrains.kotlin.jvm"
    const val spring_boot = "org.springframework.boot"
    const val detekt = "io.gitlab.arturbosch.detekt"
    const val detekt_formatting = "io.gitlab.arturbosch.detekt:detekt-formatting"
    const val spring_dependency_management = "io.spring.dependency-management"
    const val spring_kotlin = "org.jetbrains.kotlin.plugin.spring"
    const val update_dependencies = "com.github.ben-manes.versions"
    const val owasp_dependencies = "org.owasp.dependencycheck"
    const val pitest = "info.solidsoft.pitest"
    const val test_fixtures = "java-test-fixtures"
    const val allure = "io.qameta.allure"
    const val gatling = "io.gatling.gradle"
    const val jib = "com.google.cloud.tools.jib"
    const val ktlint = "org.jlleitschuh.gradle.ktlint"
}