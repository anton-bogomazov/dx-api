package com.abogomazov.userapi.convention

plugins {
    id("com.abogomazov.userapi.convention.kotlin-jvm")
}

apply {
    plugin(Plugins.spring_kotlin)
}

dependencies {
    implementation(platform(CoreLibs.spring_dependencies))
    implementation(CoreLibs.spring_boot_starter)
    implementation(ObservabilityLibs.spring_boot_starter_logging)

    testImplementation(TestingLibs.spring_boot_starter_test)
    testImplementation(TestingLibs.kotest_spring)
    testImplementation(TestingLibs.mockk_spring)
}