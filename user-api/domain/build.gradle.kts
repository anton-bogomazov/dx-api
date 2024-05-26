plugins {
    id(Conventions.kotlin_module)
}

dependencies {
    implementation(project(":common"))

    testFixturesImplementation(TestingLibs.kotest_arrow)
}
