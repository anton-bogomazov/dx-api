package com.abogomazov.userapi.convention

plugins {
    id("com.abogomazov.userapi.convention.kotlin-jvm")
}

apply {
    plugin(Plugins.spring_boot)
    plugin(Plugins.spring_kotlin)
    plugin(Plugins.jib)
}
