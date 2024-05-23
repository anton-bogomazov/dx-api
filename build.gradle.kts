val parentProjectDir = projectDir

plugins {
    id(Plugins.ktlint) version Plugins.Versions.ktlint apply false
    id(Plugins.jib) version Plugins.Versions.jib apply false
    id(Plugins.spring_kotlin) version Plugins.Versions.spring_kotlin apply false
    id(Plugins.spring_boot) version Plugins.Versions.spring_boot apply false
}

group = "com.abogomazov"
version = "1.0-SNAPSHOT"