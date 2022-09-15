package net.purefunc.kotlin.ddd.domain.entity

abstract class DomainAggRoot<ID : DomainEntityId<ID>> {
    abstract val entityId: ID
}
