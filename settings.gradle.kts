plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "cd"

include("user-api")

include("tests")
include("tests:e2e")
include("tests:performance")
include("tests:common")
