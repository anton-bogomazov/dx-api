package performance

import org.testcontainers.containers.DockerComposeContainer
import org.testcontainers.containers.wait.strategy.Wait

class StandContainer(private val configuration: StandConfiguration) {
    lateinit var dockerComposeContainer: DockerComposeContainer<Nothing>

    fun start() {
        if (configuration.startDocker) {
            println("\nStarting stand with settings:\n$configuration\n")
            dockerComposeContainer =
                DockerComposeContainer<Nothing>(configuration.dockerCompose).apply {
                    waitingFor(
                        "user-api",
                        Wait.forLogMessage(".*Started UserApiApplicationKt in.*", 1),
                    )
                    withEnv(configuration.dockerComposeEnv)
                    start()
                }
        } else {
            println("Docker disabled")
        }
    }

    fun stop() {
        if (configuration.startDocker) {
            dockerComposeContainer.stop()
        }
    }
}
