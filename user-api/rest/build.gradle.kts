plugins {
    id(Conventions.spring_module)
}

dependencies {
    implementation(project(":common"))
    implementation(project(":user-api:domain"))
    implementation(project(":user-api:usecase"))

    implementation(Libs.spring_boot_starter_web)
    implementation(Libs.spring_boot_started_hateoas)
    implementation(Libs.jackson_kotlin)
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

    testImplementation(testFixtures(project(":user-api:domain")))
    testFixturesImplementation(Libs.spring_boot_starter_test)
}
