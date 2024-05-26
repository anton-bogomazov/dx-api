plugins {
    id(Conventions.spring_app)
}

dependencies {
    implementation(ObservabilityLibs.spring_admin_server)
}

version = "1.0.0"

jib {
    container {
        ports = listOf("8060")
        mainClass = "com.abogomazov.userapi.admin.SpringBootAdminServerApplicationKt"
    }
    to {
        image = "abogomazov/spring-boot-admin:$version"
    }
}
