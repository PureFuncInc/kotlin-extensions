package net.purefunc.kotlin.ddd.domain.entity

abstract class DomainModifyCmd<ID : DomainEntityId<ID>> {
    abstract val entityId: ID
}
