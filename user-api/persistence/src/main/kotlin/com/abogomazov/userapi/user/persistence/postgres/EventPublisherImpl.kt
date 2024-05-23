package com.abogomazov.userapi.user.persistence.postgres

import com.abogomazov.userapi.common.events.DomainEventListener
import com.abogomazov.userapi.common.events.DomainEventPublisher
import com.abogomazov.userapi.common.types.DomainEvent
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kotlin.reflect.KClass

class EventPublisherImpl : DomainEventPublisher {
    private val logger = LoggerFactory.getLogger(EventPublisherImpl::class.java)
    private val listenerMap = HashMap<KClass<*>, MutableList<DomainEventListener<out DomainEvent>>>()

    fun registerListener(listener: DomainEventListener<out DomainEvent>) {
        listenerMap.compute(listener.eventType()) { _, value ->
            val list = value ?: ArrayList()
            list.add(listener)
            list
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun publish(events: Collection<DomainEvent>) {
        events.forEach { e ->
            logger.info("Processing event: $e")
            listenerMap[e::class]?.let { listeners ->
                sendEvents(listeners as List<DomainEventListener<in DomainEvent>>, e)
            }
        }
    }

    private fun sendEvents(
        listeners: List<DomainEventListener<in DomainEvent>>,
        event: DomainEvent,
    ) {
        listeners.forEach { l ->
            l.handle(event)
        }
    }
}

@Configuration
class DomainEventPublisherConfiguration {
    @Bean
    fun domainEventPublisher(): DomainEventPublisher = EventPublisherImpl()
}
