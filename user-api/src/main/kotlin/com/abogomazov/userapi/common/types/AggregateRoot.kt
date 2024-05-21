package com.abogomazov.userapi.common.types

@Suppress("UnnecessaryAbstractClass")
abstract class AggregateRoot<T>(id: T, version: Version) : DomainEntity<T>(id, version)
