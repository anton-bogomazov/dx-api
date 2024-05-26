plugins {
    id(Conventions.spring_module)
}

dependencies {
    implementation(project(":common"))
    implementation(project(":user-api:domain"))
    implementation(project(":user-api:usecase"))

    implementation(WebLibs.spring_boot_starter_web)
    implementation(WebLibs.spring_boot_started_hateoas)
    implementation(WebLibs.jackson_kotlin)
    implementation(WebLibs.openapi_ui)

    testImplementation(testFixtures(project(":user-api:domain")))

    testFixturesImplementation(TestingLibs.spring_boot_starter_test)
}
