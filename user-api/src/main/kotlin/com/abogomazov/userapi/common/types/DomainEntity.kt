package com.abogomazov.userapi.common.types

open class DomainEntity<T> protected constructor(
    val id: T,
    var version: Version,
) {
    private var events = ArrayList<DomainEvent>()

    protected fun addEvent(event: DomainEvent) {
        if (events.isEmpty() && !version.isFirst()) {
            version = version.next()
        }
        events.add(event)
    }

    fun popEvents(): List<DomainEvent> {
        val res = events
        events = ArrayList()
        return res
    }
}

data class Version internal constructor(private val value: Long) : ValueObject {
    fun toLongValue() = value

    fun next() = Version(value + 1)

    fun previous() = Version(value - 1)

    fun isFirst() = value == 0L

    companion object {
        fun new() = Version(0L)

        fun from(value: Long) = Version(value)
    }
}
