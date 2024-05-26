object PersistenceLibs {
    private object Versions {
        const val postgresql = "42.7.3"
        const val flyway = "10.13.0"
    }

    const val postgres_driver = "org.postgresql:postgresql:${Versions.postgresql}"
    const val flyway = ("org.flywaydb:flyway-database-postgresql:${Versions.flyway}")
    const val spring_data_jdbc = "org.springframework.boot:spring-boot-starter-data-jdbc"
}