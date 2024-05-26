plugins {
    id(Conventions.spring_module)
}

dependencies {
    implementation(project(":common"))
    implementation(project(":user-api:domain"))
    implementation(project(":user-api:usecase"))

    runtimeOnly(PersistenceLibs.postgres_driver)
    runtimeOnly(PersistenceLibs.flyway)
    implementation(PersistenceLibs.spring_data_jdbc)

    testImplementation(testFixtures(project(":user-api:domain")))
    testImplementation(TestingLibs.testcontainers_spring)
    testImplementation(TestingLibs.testcontainers_postgres)
}
