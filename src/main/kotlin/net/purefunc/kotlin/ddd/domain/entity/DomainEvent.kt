package net.purefunc.kotlin.ddd.domain.entity

abstract class DomainEvent<ID : DomainEntityId> {
    abstract val entityId: ID
}
