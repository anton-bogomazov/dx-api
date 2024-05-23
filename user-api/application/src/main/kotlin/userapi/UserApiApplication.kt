package userapi

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class UserApiApplication

fun main() {
    SpringApplication
        .run(UserApiApplication::class.java)
}
