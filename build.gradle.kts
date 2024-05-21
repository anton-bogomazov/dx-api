import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt

val parentProjectDir = projectDir

plugins {
    kotlin("jvm") version "1.9.24" apply false
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.24" apply false
    id("com.google.cloud.tools.jib") version "3.4.2" apply false
    id("io.gitlab.arturbosch.detekt") version "1.23.6"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    id("com.github.ben-manes.versions") version "0.51.0" apply false
}

subprojects {

    group = "com.abogomazov"
    version = "1.0-SNAPSHOT"

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("org.jetbrains.kotlin.plugin.spring")
        plugin("com.google.cloud.tools.jib")
        plugin("io.gitlab.arturbosch.detekt")
        plugin("org.jlleitschuh.gradle.ktlint")
        plugin("com.github.ben-manes.versions")
        plugin("java-test-fixtures")
    }

    repositories {
        mavenCentral()
    }

// DETEKT CONFIG
    detekt {
        buildUponDefaultConfig = true
        allRules = false
        config.setFrom("$parentProjectDir/tools/detekt/detekt.yml")
    }
    tasks.withType<Detekt>().configureEach {
        reports {
            html.required.set(true)
        }
    }

// DEPENDENCY UPDATE CONFIG
    tasks.withType<DependencyUpdatesTask> {
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
}