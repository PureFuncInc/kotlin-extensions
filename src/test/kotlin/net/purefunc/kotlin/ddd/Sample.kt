package net.purefunc.kotlin.ddd

import net.purefunc.kotlin.ddd.domain.entity.DomainAggRoot
import net.purefunc.kotlin.ddd.domain.entity.DomainEntity
import net.purefunc.kotlin.ddd.domain.entity.DomainEntityId
import net.purefunc.kotlin.ddd.domain.entity.DomainEvent
import net.purefunc.kotlin.ddd.domain.entity.DomainModifyCmd
import net.purefunc.kotlin.ddd.domain.vo.DomainVO
import net.purefunc.kotlin.ddd.infrastructure.enu.Deleted
import net.purefunc.kotlin.ddd.infrastructure.po.BasePo

data class SampleAggRoot(
    val key: Key,
    val value: String,
    override val entityId: SampleId,
) : DomainAggRoot<SampleId>()

data class SampleEntity(
    val key: Key,
    override val entityId: SampleId,
) : DomainEntity<SampleId>()

data class SampleEvent(
    val key: Key,
    val name: String,
    override val entityId: SampleId,
) : DomainEvent<SampleId>()

data class SampleModifyCmd(
    val key: Key,
    val value: String,
    override val entityId: SampleId,
) : DomainModifyCmd<SampleId>()

data class SampleId(
    override val uuid: Long,
    override val createUser: String,
    override val lastModifiedUser: String,
    override val memo: String,
) : DomainEntityId()

data class Key(
    override val memo: String,
) : DomainVO()

data class SamplePO(
    val id: Long?,
    override val uuid: Long,
    override var deleted: Deleted,
    override val createDate: Long,
    override var lastModifiedDate: Long,
    override val createUser: String,
    override var lastModifiedUser: String,
    override var memo: String,
    override val version: Long,
) : BasePo()
