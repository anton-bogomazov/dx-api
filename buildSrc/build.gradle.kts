plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
    mavenCentral()
}

val kotlinVersion = "1.9.24"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.6")
    implementation("com.github.ben-manes:gradle-versions-plugin:0.51.0")
}

kotlin {
    jvmToolchain(21)
}