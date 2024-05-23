package com.abogomazov.userapi.convention

plugins {
    id("com.abogomazov.userapi.convention.kotlin-jvm")
}

apply {
    plugin(Plugins.spring_kotlin)
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.2.5"))
    implementation(Libs.spring_boot_starter_logging)

    testImplementation(Libs.spring_boot_starter_test)
    testImplementation(Libs.kotest_spring)
    testImplementation(Libs.mockk_spring)
}