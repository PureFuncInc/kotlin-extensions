package net.purefunc.kotlin.ddd.domain.repository

import arrow.core.Either
import net.purefunc.kotlin.ddd.domain.entity.DomainAggregateRoot
import net.purefunc.kotlin.ddd.domain.entity.DomainEntityId
import net.purefunc.kotlin.ext.AppErr

interface DomainRepository<ID : DomainEntityId, R : DomainAggregateRoot<ID>> {

    suspend fun save(entity: R): Either<AppErr, R>

    suspend fun findById(id: ID): Either<AppErr, R?>

    suspend fun existsById(id: ID): Either<AppErr, Boolean>

    suspend fun count(): Either<AppErr, Long>

    suspend fun deleteById(id: ID): Either<AppErr, Unit>

    suspend fun delete(entity: R): Either<AppErr, Unit>
}
