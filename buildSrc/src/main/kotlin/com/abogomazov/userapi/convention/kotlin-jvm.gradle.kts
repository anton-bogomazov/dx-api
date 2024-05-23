package com.abogomazov.userapi.convention

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import io.gitlab.arturbosch.detekt.Detekt
import info.solidsoft.gradle.pitest.PitestPluginExtension

plugins {
    kotlin("jvm")
}

apply {
    plugin("java")
    plugin("jacoco")
    plugin(Plugins.test_fixtures)
    plugin(Plugins.detekt)
    plugin(Plugins.ktlint)
    plugin(Plugins.update_dependencies)
    plugin(Plugins.pitest)
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(Libs.arrow)

    implementation(Libs.slf4j_api)

    testImplementation(Libs.kotest_junit)
    testImplementation(Libs.kotest_assert)
    testImplementation(Libs.kotest_arrow)
    testImplementation(Libs.mockk)

    testImplementation(Libs.kotest_pitest)
}

tasks {
    val failOnWarning = project.properties["allWarningsAsErrors"] != null &&
            project.properties["allWarningsAsErrors"] == "true"
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_21.toString()
            allWarningsAsErrors = failOnWarning
            freeCompilerArgs = listOf("-Xjvm-default=all-compatibility")
        }
    }
    withType<JavaCompile> {
        options.compilerArgs.add("-Xlint:all")
        targetCompatibility = JavaVersion.VERSION_21.toString()
    }

    withType<Test> {
        useJUnitPlatform()

        testLogging {
            events(
                org.gradle.api.tasks.testing.logging.TestLogEvent.PASSED,
                org.gradle.api.tasks.testing.logging.TestLogEvent.FAILED,
                org.gradle.api.tasks.testing.logging.TestLogEvent.SKIPPED
            )
            showStandardStreams = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }

//        systemProperties["pact.rootDir"] = "${rootProject.buildDir}/pacts"
    }

    val check = named<DefaultTask>("check")
    val jacocoTestReport = named<JacocoReport>("jacocoTestReport")
    val jacocoTestCoverageVerification = named<JacocoCoverageVerification>("jacocoTestCoverageVerification")

    check {
        finalizedBy(jacocoTestReport)
        finalizedBy(named<DependencyUpdatesTask>("dependencyUpdates"))
    }

    jacocoTestReport {
        dependsOn(check)
        finalizedBy(jacocoTestCoverageVerification)
    }

    jacocoTestCoverageVerification {
        dependsOn(jacocoTestReport)

        violationRules {
            rule {
                excludes = listOf("application")
                limit {
                    minimum = BigDecimal("0.7")
                }
            }
        }
    }

    withType<Detekt>().configureEach {
        buildUponDefaultConfig = true
        allRules = false
        config.setFrom("${rootProject.rootDir}/tools/detekt/detekt.yml")
        reports {
            html.required.set(true)
        }
    }

    withType<DependencyUpdatesTask> {
        revision = "release"
        outputFormatter = "txt"
        checkForGradleUpdate = true
        outputDir = "${buildDir}/reports/dependencies"
        reportfileName = "updates"

        fun isNonStable(version: String): Boolean {
            val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.uppercase().contains(it) }
            val regex = "^[0-9,.v-]+(-r)?$".toRegex()
            val isStable = stableKeyword || regex.matches(version)
            return isStable.not()
        }
        rejectVersionIf {
            isNonStable(candidate.version) && !isNonStable(currentVersion)
        }
    }

    configure<PitestPluginExtension> {
        targetClasses.set(listOf("com.abogomazov.userapi.*"))
    }
}