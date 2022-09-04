package net.purefunc.kotlin.ddd.domain.entity

abstract class DomainModifyCmd<ID : DomainEntityId> {
    abstract val entityId: ID
}
