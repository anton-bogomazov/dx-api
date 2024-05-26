plugins {
    id(Conventions.spring_app)
}

dependencies {
    implementation(ObservabilityLibs.spring_admin_server)
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
