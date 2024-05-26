object ObservabilityLibs {
    object Versions {
        const val spring_admin = "3.2.3"
        const val sleuth = "3.1.11"
        const val loki_appender = "1.5.1"
        const val slf4j = "1.7.32"
        const val micrometer = "1.13.0"
    }
    const val actuator = "org.springframework.boot:spring-boot-starter-actuator"
    const val micrometer_prometheus = "io.micrometer:micrometer-registry-prometheus"
    const val micrometer = "io.micrometer:micrometer-core:${Versions.micrometer}"
    const val sleuth = "org.springframework.cloud:spring-cloud-starter-sleuth:${Versions.sleuth}"
    const val loki_appender = "com.github.loki4j:loki-logback-appender:${Versions.loki_appender}"
    const val spring_admin_server = "de.codecentric:spring-boot-admin-starter-server:${Versions.spring_admin}"
    const val spring_admin_client = "de.codecentric:spring-boot-admin-starter-client:${Versions.spring_admin}"
    const val slf4j_api = "org.slf4j:slf4j-api:${Versions.slf4j}"
    const val spring_boot_starter_logging = "org.springframework.boot:spring-boot-starter-logging"
}
