package com.abogomazov.userapi.tests

import java.io.File
import java.io.FileInputStream
import java.util.Properties

class StandConfiguration {
    companion object {
        const val DOCKER_COMPOSE_FILE_PROPERTY = "dockerComposeFile"
        const val ENV_FILE_PROPERTY = "envFile"
        const val START_DOCKER_PROPERTY = "startDocker"
        const val HOST_TEMPLATE = "http://localhost:"
        const val USER_API_PORT_PROPERTY = "USER_API_PORT"
    }

    val usersBaseUrl: String
    val dockerCompose: File
    val dockerComposeEnv: Map<String, String>
    val startDocker: Boolean

    init {
        val dockerComposeProperty =
            requireNotNull(System.getProperty(DOCKER_COMPOSE_FILE_PROPERTY)) {
                "Property $DOCKER_COMPOSE_FILE_PROPERTY is null"
            }
        dockerCompose = File(dockerComposeProperty)

        val envProperty =
            requireNotNull(System.getProperty(ENV_FILE_PROPERTY)) {
                "Property $ENV_FILE_PROPERTY is null"
            }

        startDocker =
            requireNotNull(System.getProperty(START_DOCKER_PROPERTY)) {
                "Property $START_DOCKER_PROPERTY is null"
            }.toBoolean()

        val env = File(envProperty)
        val props = Properties()
        props.load(FileInputStream(env))

        usersBaseUrl = HOST_TEMPLATE + props.property(USER_API_PORT_PROPERTY)
        dockerComposeEnv = props.entries.associate { it.key.toString() to it.value.toString() }
    }

    private fun Properties.property(name: String) =
        requireNotNull(getProperty(name)) {
            "Property $name must not be null"
        }

    override fun toString(): String {
        return "StandConfiguration(usersBaseUrl='$usersBaseUrl', \n" +
            "dockerCompose=$dockerCompose, \n" +
            "dockerComposeEnv=$dockerComposeEnv, \n" +
            "startDocker=$startDocker)"
    }
}
