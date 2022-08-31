package net.purefunc.kotlin.ddd

import net.purefunc.kotlin.ddd.domain.entity.DomainEntity
import net.purefunc.kotlin.ddd.domain.entity.DomainEntityId
import net.purefunc.kotlin.ddd.domain.vo.DomainValueObject
import net.purefunc.kotlin.ddd.infrastructure.enu.Deleted
import net.purefunc.kotlin.ddd.infrastructure.po.BasePo

data class SamplePO(
    val id: Long?,
    override val uuid: Long,
    override val deleted: Deleted,
    override val createDate: Long,
    override val lastModifiedDate: Long,
    override val createUser: String,
    override val lastModifiedUser: String,
    override val memo: String,
) : BasePo()

data class SampleId(
    override val identify: Int,
    override val uuid: Long,
    override val memo: String,
) : DomainEntityId()

data class SampleAggregateRoot<SampleId : DomainEntityId>(
    override val entityId: SampleId,
    val key: Key,
) : DomainEntity<SampleId>()

data class SampleEntity<SampleId : DomainEntityId>(
    override val entityId: SampleId,
    val key: Key,
) : DomainEntity<SampleId>()

data class Key(
    override val memo: String,
) : DomainValueObject()
