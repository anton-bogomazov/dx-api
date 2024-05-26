object WebLibs {
    private object Versions {
        const val jackson = "2.17.1"
    }

    const val spring_boot_starter_web = "org.springframework.boot:spring-boot-starter-web"
    const val spring_boot_started_hateoas = "org.springframework.boot:spring-boot-starter-hateoas"
    const val jackson_kotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:${Versions.jackson}"
    const val jackson_databind = "com.fasterxml.jackson.core:jackson-databind:${Versions.jackson}"
    const val openapi_ui = "org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0"
}