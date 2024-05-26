plugins {
    com.abogomazov.userapi.convention.`observable-spring-application`
}

dependencies {
    implementation(project(":common"))
    implementation(project(":user-api:domain"))
    implementation(project(":user-api:usecase"))
    implementation(project(":user-api:persistence"))
    implementation(project(":user-api:rest"))

    testImplementation(Libs.arch_unit)
    testImplementation(testFixtures(project(":user-api:domain")))

    testFixturesImplementation(testFixtures(project(":user-api:usecase")))
}
