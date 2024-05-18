plugins {
    kotlin("jvm") version "1.9.23"
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.24"
}

group = "com.abogomazov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// TODO autoupdate deps
dependencies {
    implementation("org.springframework.boot:spring-boot-starter:3.2.5")
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.5")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc:3.2.5")
    implementation("io.arrow-kt:arrow-core:1.2.4")
    runtimeOnly("org.postgresql:postgresql:42.7.3")
    runtimeOnly("org.flywaydb:flyway-database-postgresql:10.13.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.5")
    testImplementation("org.springframework.boot:spring-boot-testcontainers:3.2.5")
    testImplementation("io.kotest.extensions:kotest-assertions-arrow:1.4.0")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.9.0")
    testImplementation("io.kotest:kotest-assertions-core-jvm:5.9.0")
    testImplementation("org.testcontainers:postgresql:1.19.8")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    testImplementation("com.tngtech.archunit:archunit:1.3.0")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}