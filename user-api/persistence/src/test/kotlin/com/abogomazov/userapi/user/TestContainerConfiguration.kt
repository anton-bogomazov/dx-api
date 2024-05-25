package com.abogomazov.userapi.user

import io.kotest.core.listeners.AfterTestListener
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.jdbc.JdbcTestUtils
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.utility.DockerImageName

@SpringBootTest(classes = [TestUserApiApplication::class])
@ContextConfiguration(classes = [TestContainerConfiguration::class])
interface PersistenceTest

class AfertestUserCleaner(
    private val jdbcTemplate: JdbcTemplate,
) : AfterTestListener {
    override suspend fun afterTest(
        testCase: TestCase,
        result: TestResult,
    ) {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users")
    }
}

@TestConfiguration(proxyBeanMethods = false)
class TestContainerConfiguration {
    @Bean
    @ServiceConnection
    fun postgresContainer(): PostgreSQLContainer<*> = PostgreSQLContainer(DockerImageName.parse("postgres"))
}

@SpringBootApplication
class TestUserApiApplication
