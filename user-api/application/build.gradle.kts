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

jib {
    container {
        ports = listOf("8080")
        mainClass = "com.abogomazov.userapi.UserApiApplicationKt"
    }
    to {
        image = "abogomazov/user-api"
    }
}
