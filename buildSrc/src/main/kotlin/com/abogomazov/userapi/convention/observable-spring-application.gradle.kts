package com.abogomazov.userapi.convention

plugins {
    id("com.abogomazov.userapi.convention.spring-application")
}

dependencies {
    implementation(ObservabilityLibs.actuator)
    implementation(ObservabilityLibs.micrometer_prometheus)
    implementation(ObservabilityLibs.spring_admin_client)
    implementation(ObservabilityLibs.sleuth)
    implementation(ObservabilityLibs.loki_appender)
}