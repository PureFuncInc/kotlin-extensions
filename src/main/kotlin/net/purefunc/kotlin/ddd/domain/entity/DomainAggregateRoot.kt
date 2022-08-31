package net.purefunc.kotlin.ddd.domain.entity

abstract class DomainAggregateRoot<ID : DomainEntityId> {
    abstract val entityId: ID
}
