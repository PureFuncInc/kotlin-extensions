package net.purefunc.kotlin.ddd.domain.entity

abstract class DomainAggRoot<ID : DomainEntityId> {
    abstract val entityId: ID
}
