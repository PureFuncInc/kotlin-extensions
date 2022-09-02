package net.purefunc.kotlin.ddd.domain.repository

import arrow.core.Either
import net.purefunc.kotlin.ddd.domain.entity.DomainAggregateRoot
import net.purefunc.kotlin.ddd.domain.entity.DomainEntityId
import net.purefunc.kotlin.ext.AppErr

interface DomainRepository<ID : DomainEntityId, R : DomainAggregateRoot<ID>, E : AppErr> {

    suspend fun save(entity: R): Either<E, R>

    suspend fun findById(id: ID): Either<E, R?>

    suspend fun existsById(id: ID): Either<E, Boolean>

    suspend fun count(): Either<E, Long>

    suspend fun deleteById(id: ID): Either<E, Unit>

    suspend fun delete(entity: R): Either<E, Unit>
}
