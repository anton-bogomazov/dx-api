package com.abogomazov.userapi.tests.performance

import com.abogomazov.userapi.tests.StandConfiguration
import com.abogomazov.userapi.tests.StandContainer
import io.gatling.javaapi.core.CoreDsl.constantUsersPerSec
import io.gatling.javaapi.core.CoreDsl.global
import io.gatling.javaapi.core.CoreDsl.scenario
import io.gatling.javaapi.core.Simulation
import io.gatling.javaapi.http.HttpDsl.http
import io.restassured.RestAssured
import io.restassured.http.ContentType
import net.datafaker.Faker
import java.net.URL

@Suppress("MagicNumber")
class GetUsersSimulation : Simulation() {
    private val settings = StandConfiguration()
    private val simulationSettings = Settings()
    private val standContainer = StandContainer(settings)
    private val usersUrl: URL
        get() = URL("${settings.usersBaseUrl}/api/v1/users")

    init {
        println("Simulation settings:\n$simulationSettings\n")
        standContainer.start()

        val httpProtocol = http.baseUrl(settings.usersBaseUrl)

        val scn =
            scenario("Get users")
                .exec(
                    http("Get users request")
                        .get(usersUrl.toString()),
                )

        setUp(
            scn.injectOpen(
                constantUsersPerSec(simulationSettings.usersPerSec)
                    .during(simulationSettings.duration.inWholeSeconds),
            )
                .protocols(httpProtocol),
        )
            .assertions(global().successfulRequests().percent().`is`(100.0))
            .assertions(global().responseTime().percentile4().lte(100))
    }

    override fun before() {
        val faker = Faker()
        repeat(simulationSettings.usersSize) {
            createUser(
                firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
            )
        }
    }

    override fun after() {
        standContainer.stop()
    }

    private fun createUser(
        firstName: String,
        lastName: String,
    ) {
        RestAssured.given()
            .body("""{ "firstName": "$firstName", "lastName": "$lastName"}""")
            .contentType(ContentType.JSON)
            .post(usersUrl)
            .then()
            .statusCode(201)
    }
}
