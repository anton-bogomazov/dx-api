package com.abogomazov.userapi.common.events

import com.abogomazov.userapi.common.types.DomainEvent

interface DomainEventPublisher {
    fun publish(events: Collection<DomainEvent>)
}