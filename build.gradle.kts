import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import io.gitlab.arturbosch.detekt.Detekt

val parentProjectDir = projectDir

plugins {
    id(Plugins.kotlin) version Plugins.Versions.kotlin apply false
    id(Plugins.detekt) version Plugins.Versions.detekt
    id(Plugins.ktlint) version Plugins.Versions.ktlint
    id(Plugins.update_dependencies) version Plugins.Versions.update_dependencies
}

subprojects {

    group = "com.abogomazov"
    version = "1.0-SNAPSHOT"

    apply {
        plugin("java")
        plugin("jacoco")
        plugin(Plugins.kotlin)
        plugin(Plugins.detekt)
        plugin(Plugins.ktlint)
        plugin(Plugins.update_dependencies)
        plugin(Plugins.test_fixtures)
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