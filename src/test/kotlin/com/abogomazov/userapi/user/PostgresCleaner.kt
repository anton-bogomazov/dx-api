package com.abogomazov.userapi.user

import io.kotest.core.listeners.AfterTestListener
import io.kotest.core.test.TestCase
import io.kotest.core.test.TestResult
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.jdbc.JdbcTestUtils

class PostgresCleaner(
    private val jdbcTemplate: JdbcTemplate,
) : AfterTestListener {
    override suspend fun afterTest(
        testCase: TestCase,
        result: TestResult,
    ) {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, "users")
    }
}
