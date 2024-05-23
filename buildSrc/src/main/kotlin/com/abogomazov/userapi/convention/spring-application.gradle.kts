package com.abogomazov.userapi.convention

plugins {
    id("com.abogomazov.userapi.convention.spring-module")
}

apply {
    plugin(Plugins.spring_boot)
    plugin(Plugins.jib)
}

dependencies {
}