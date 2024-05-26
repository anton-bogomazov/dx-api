plugins {
    id(Conventions.spring_app)
}

dependencies {
    implementation(ObservabilityLibs.spring_admin_server)
    implementation(ObservabilityLibs.spring_boot_starter_logging)
    implementation(ObservabilityLibs.slf4j_api)
}

jib {
    container {
        ports = listOf("8060")
        mainClass = "com.abogomazov.userapi.admin.SpringBootAdminServerApplicationKt"
    }
    to {
        image = "abogomazov/spring-boot-admin"
    }
}
