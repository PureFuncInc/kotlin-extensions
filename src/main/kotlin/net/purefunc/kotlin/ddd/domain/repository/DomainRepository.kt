package net.purefunc.kotlin.ddd.domain.repository

import net.purefunc.kotlin.ddd.domain.entity.DomainAggregateRoot
import net.purefunc.kotlin.ddd.domain.entity.DomainEntityId

interface DomainRepository<ID : DomainEntityId, R : DomainAggregateRoot<ID>> {

    suspend fun findByEntityId(entityId: ID): R?
}
