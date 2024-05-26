plugins {
    id(Conventions.observable_spring_app)
}

dependencies {
    implementation(project(":common"))
    implementation(project(":user-api:domain"))
    implementation(project(":user-api:usecase"))
    implementation(project(":user-api:persistence"))
    implementation(project(":user-api:rest"))
    implementation(project(":user-api:metrics"))

    testImplementation(TestingLibs.arch_unit)
    testImplementation(testFixtures(project(":user-api:domain")))

    testFixturesImplementation(testFixtures(project(":user-api:usecase")))
    testFixturesImplementation(TestingLibs.arch_unit)
}

jib {
    container {
        ports = listOf("8080")
        mainClass = "com.abogomazov.userapi.UserApiApplicationKt"
    }
    to {
        image = "abogomazov/user-api"
    }
}
