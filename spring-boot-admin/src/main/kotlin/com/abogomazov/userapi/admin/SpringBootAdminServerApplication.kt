package com.abogomazov.userapi.admin

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@EnableAdminServer
@SpringBootApplication
class SpringBootAdminServerApplication

fun main() {
    SpringApplication.run(SpringBootAdminServerApplication::class.java)
}
