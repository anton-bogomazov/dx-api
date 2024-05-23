package com.abogomazov.userapi.common.events

import com.abogomazov.userapi.common.types.DomainEvent
import kotlin.reflect.KClass

interface DomainEventListener<T : DomainEvent> {
    fun eventType(): KClass<T>

    fun handle(event: T)
}
