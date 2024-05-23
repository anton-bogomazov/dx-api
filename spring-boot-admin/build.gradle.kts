plugins {
    com.abogomazov.userapi.convention.`spring-application`
}

dependencies {
    implementation("de.codecentric:spring-boot-admin-starter-server:3.2.3")
    implementation(Libs.spring_boot_starter_logging)
    implementation(Libs.slf4j_api)
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
