plugins {
    application
    kotlin("jvm") version "1.9.24"
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.24"
    id("com.google.cloud.tools.jib") version "3.4.2"
    id("java-test-fixtures")
}

val springVer = "3.2.5"
val kotestVer = "5.9.0"

dependencies {
    // COMMON
    implementation("org.springframework.boot:spring-boot-starter:$springVer")
    implementation("io.arrow-kt:arrow-core:1.2.4")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springVer")
    testImplementation("io.kotest.extensions:kotest-assertions-arrow:1.4.0")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVer")
    testImplementation("io.kotest:kotest-assertions-core-jvm:$kotestVer")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    testImplementation("com.tngtech.archunit:archunit:1.3.0")
    testImplementation("io.mockk:mockk:1.13.11")
    testImplementation("com.ninja-squad:springmockk:4.0.2")

    testFixturesImplementation(project(":user-api"))
    testFixturesImplementation("io.kotest.extensions:kotest-assertions-arrow:1.4.0")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-test:$springVer")

    // REST
    implementation("org.springframework.boot:spring-boot-starter-web:$springVer")
    implementation("org.springframework.hateoas:spring-hateoas:2.3.0")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.1")

    // PERSISTENCE
    runtimeOnly("org.postgresql:postgresql:42.7.3")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:10.13.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc:$springVer")

    testImplementation("org.springframework.boot:spring-boot-testcontainers:$springVer")
    testImplementation("org.testcontainers:postgresql:1.19.8")
}

val mainClass by extra("com.abogomazov.userapi.MainKt")

jib {
    container {
        ports = listOf("8080")
        mainClass = mainClass
    }
    to {
        image = "abogomazov/user-api"
    }
}

kotlin {
    jvmToolchain(21)
}
