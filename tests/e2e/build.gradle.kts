plugins {
    id(Conventions.kotlin_module)
    id(Plugins.allure) version Plugins.Versions.allure
}

allure {
    adapter {
        version.set(Plugins.Versions.allure_cli)
        allureJavaVersion.set(Plugins.Versions.allure_java)

        frameworks {
            junit5 {
                enabled.set(false)
            }
        }
    }
}
dependencies {
    testImplementation(TestingLibs.kotest_junit)
    testImplementation(TestingLibs.kotest_arrow)
    testImplementation(TestingLibs.testcontainers_core)
    testImplementation(project(":tests:common"))

    testImplementation(TestingLibs.rest_assured)
    testImplementation(WebLibs.jackson_kotlin)
    testImplementation(WebLibs.jackson_databind)
    testImplementation(CoreLibs.koin)

    testFixturesImplementation(TestingLibs.faker)
}

tasks.withType<Test> {
    val dockerComposeFileProperty = "dockerComposeFile"
    val envFileProperty = "envFile"
    val startDockerProperty = "startDocker"

    val dockerComposeFile =
        System.getProperty(
            dockerComposeFileProperty,
            "${project.rootProject.rootDir}/tools/docker/docker-compose.yml",
        )
    systemProperty(dockerComposeFileProperty, dockerComposeFile)

    val envFile =
        System.getProperty(
            envFileProperty,
            "${project.rootProject.rootDir}/tools/docker/env/e2e.env",
        )
    systemProperty(envFileProperty, envFile)

    val startDocker = System.getProperty(startDockerProperty, "true")
    systemProperty(startDockerProperty, startDocker)
}
