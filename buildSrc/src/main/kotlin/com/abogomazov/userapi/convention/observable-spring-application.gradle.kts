package com.abogomazov.userapi.convention

plugins {
    id("com.abogomazov.userapi.convention.spring-application")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("de.codecentric:spring-boot-admin-starter-client:3.2.3")
    implementation("org.springframework.cloud:spring-cloud-starter-sleuth:3.1.11")
    implementation("com.github.loki4j:loki-logback-appender:1.5.1")
}