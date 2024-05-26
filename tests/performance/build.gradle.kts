plugins {
    id(Conventions.kotlin_module)
    id(Plugins.gatling) version Plugins.Versions.gatling
}

dependencies {
    gatlingImplementation(project(":tests:common"))
    gatlingImplementation(TestingLibs.rest_assured)
    gatlingImplementation(TestingLibs.rest_assured_kotlin)
    gatlingImplementation(TestingLibs.faker)
    gatlingImplementation(TestingLibs.testcontainers_core)
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
