package com.abogomazov.userapi.convention

plugins {
    id("com.abogomazov.userapi.convention.kotlin-jvm")
}

apply {
    plugin(Plugins.spring_boot)
    plugin(Plugins.spring_kotlin)
    plugin(Plugins.jib)
}

dependencies {
    implementation(Libs.spring_boot_starter_logging)

    testImplementation(Libs.spring_boot_starter_test)
    testImplementation(Libs.kotest_spring)
    testImplementation(Libs.mockk_spring)

    testImplementation(Libs.arch_unit)
}