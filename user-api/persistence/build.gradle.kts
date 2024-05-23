plugins {
    com.abogomazov.userapi.convention.`spring-module`
}

dependencies {
    implementation(project(":common"))
    implementation(project(":user-api:domain"))
    implementation(project(":user-api:usecase"))

    runtimeOnly(Libs.postgres_driver)
    runtimeOnly(Libs.flyway)
    implementation(Libs.spring_data_jdbc)

    testImplementation(testFixtures(project(":user-api:domain")))
    testImplementation(Libs.testcontainers_spring)
    testImplementation(Libs.testcontainers_postgres)
}
