package net.purefunc.kotlin.ddd.domain.entity

abstract class DomainEvent<ID : DomainEntityId<ID>> {
    abstract val entityId: ID
}
