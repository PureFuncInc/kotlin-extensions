package net.purefunc.kotlin.ddd

import net.purefunc.kotlin.ddd.domain.entity.DomainEntity
import net.purefunc.kotlin.ddd.domain.entity.DomainEntityId
import net.purefunc.kotlin.ddd.domain.vo.DomainVO
import net.purefunc.kotlin.ddd.infrastructure.enu.Deleted
import net.purefunc.kotlin.ddd.infrastructure.po.BasePo

data class SamplePO(
    val id: Long?,
    override val uuid: Long,
    override var deleted: Deleted,
    override val createDate: Long,
    override var lastModifiedDate: Long,
    override val createUser: String,
    override var lastModifiedUser: String,
    override var identity: Long,
    override var memo: String,
) : BasePo()

data class SampleId(
    override val uuid: Long,
    override val createUser: String,
    override val lastModifiedUser: String,
    override val identity: Long,
    override val memo: String,
) : DomainEntityId<SampleId>() {

    override fun create(uuid: Long): SampleId =
        SampleId(
            uuid = uuid,
            createUser = "",
            lastModifiedUser = "",
            identity = 0,
            memo = "",
        )
}

data class SampleAggregateRoot<SampleId : DomainEntityId<SampleId>>(
    override val entityId: SampleId,
    val key: Key,
    val value: String,
) : DomainEntity<SampleId>()

data class SampleEntity<SampleId : DomainEntityId<SampleId>>(
    override val entityId: SampleId,
    val key: Key,
) : DomainEntity<SampleId>()

data class Key(
    override val memo: String,
) : DomainVO()
