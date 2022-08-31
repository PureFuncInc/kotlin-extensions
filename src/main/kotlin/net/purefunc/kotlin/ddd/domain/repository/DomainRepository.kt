package net.purefunc.kotlin.ddd.domain.repository

import arrow.core.Either
import net.purefunc.kotlin.ddd.domain.entity.DomainAggregateRoot
import net.purefunc.kotlin.ddd.domain.entity.DomainEntityId
import net.purefunc.kotlin.ext.AppErr

interface DomainRepository<ID : DomainEntityId, R : DomainAggregateRoot<ID>> {
    suspend fun findByEntityId(entityId: ID): Either<AppErr, R?>
}
