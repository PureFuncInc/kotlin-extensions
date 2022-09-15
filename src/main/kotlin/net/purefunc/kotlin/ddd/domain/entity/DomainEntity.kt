package net.purefunc.kotlin.ddd.domain.entity

abstract class DomainEntity<ID : DomainEntityId> {
    abstract val entityId: ID
}
