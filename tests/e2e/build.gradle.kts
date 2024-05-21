plugins {
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
    testImplementation(Libs.kotest_junit)
    testImplementation(Libs.kotest_arrow)
    testImplementation(Libs.junit_engine)
    testImplementation(Libs.junit_params)
    testImplementation(Libs.testcontainers_core)
    testImplementation(project(":tests:common"))

    testImplementation(Libs.kbdd)
    testImplementation(Libs.rest_assured)
    testImplementation(Libs.jackson_kotlin)
    testImplementation(Libs.jackson_databind)
    testImplementation(Libs.jfix_corounit_allure)
    testImplementation(Libs.jfix_corounit_engine)
    testImplementation(Libs.koin)

    testFixturesImplementation(Libs.faker)
    testFixturesImplementation(Libs.kbdd)
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

kotlin {
    jvmToolchain(21)
}
