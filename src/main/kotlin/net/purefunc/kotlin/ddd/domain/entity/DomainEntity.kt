package net.purefunc.kotlin.ddd.domain.entity

abstract class DomainEntity<ID : DomainEntityId<ID>> {
    abstract val entityId: ID
}
