plugins {
    com.abogomazov.userapi.convention.`kotlin-jvm`
}

dependencies {
    implementation(project(":common"))

    testFixturesImplementation(Libs.kotest_arrow)
}
