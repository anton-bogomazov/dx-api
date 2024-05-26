package com.abogomazov.userapi.user.metrics

import com.abogomazov.userapi.user.persistence.postgres.UserRepository
import io.micrometer.core.instrument.Gauge
import io.micrometer.core.instrument.MeterRegistry
import io.micrometer.core.instrument.binder.MeterBinder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BusinessMetricsConfiguration {
    @Bean
    fun registeredUsersCount(repository: UserRepository): MeterBinder {
        return MeterBinder { registry: MeterRegistry ->
            Gauge.builder("users_registered_count") {
                repository.count()
            }.register(registry)
        }
    }
}
