plugins {
    id(Conventions.kotlin_module)
}

dependencies {
    implementation(project(":common"))

    testFixturesImplementation(Libs.kotest_arrow)
}
