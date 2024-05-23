plugins {
    id(Plugins.kotlin)
    id(Plugins.spring_kotlin) version Plugins.Versions.spring_kotlin
    id(Plugins.jib) version Plugins.Versions.jib
}

dependencies {
    implementation("de.codecentric:spring-boot-admin-starter-server:3.2.3")
    implementation(Libs.spring_boot_starter_logging)
    implementation(Libs.slf4j_api)
}

val mainClass by extra("com.abogomazov.userapi.admin.SpringBootAdminServerApplicationKt")

jib {
    container {
        ports = listOf("8060")
        mainClass = mainClass
    }
    to {
        image = "abogomazov/spring-boot-admin"
    }
}
