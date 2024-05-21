plugins {
    kotlin("jvm") version "1.9.24"
    id("io.gatling.gradle") version "3.11.2"
}

// GATLING
dependencies {
    gatlingImplementation(project(":tests:common"))
    gatlingImplementation("io.rest-assured:rest-assured:5.4.0")
    gatlingImplementation("io.rest-assured:kotlin-extensions:5.4.0")
    gatlingImplementation("net.datafaker:datafaker:2.2.2")
    gatlingImplementation("org.testcontainers:testcontainers:1.19.8")
}

gatling {
    val dockerComposeFileProperty = "dockerComposeFile"
    val envFileProperty = "envFile"
    val startDockerProperty = "startDocker"
    val usersPerSecPropery = "usersPerSec"
    val durationProperty = "duration"
    val usersSizeProperty = "usersSize"

    val dockerComposeFile =
        System.getProperty(
            dockerComposeFileProperty,
            "${project.rootProject.rootDir}/tools/docker/docker-compose.yml",
        )
    systemProperties[dockerComposeFileProperty] = dockerComposeFile

    val envFile =
        System.getProperty(
            envFileProperty,
            "${project.rootProject.rootDir}/tools/docker/env/performance.env",
        )
    systemProperties[envFileProperty] = envFile

    val startDocker = System.getProperty(startDockerProperty, "true")
    systemProperties[startDockerProperty] = startDocker

    val usersPerSec = System.getProperty(usersPerSecPropery, "10.0")
    systemProperties[usersPerSecPropery] = usersPerSec

    val duration = System.getProperty(durationProperty, "PT10S")
    systemProperties[durationProperty] = duration

    val usersSize = System.getProperty(usersSizeProperty, "1200")
    systemProperties[usersSizeProperty] = usersSize
}

kotlin {
    jvmToolchain(21)
}
