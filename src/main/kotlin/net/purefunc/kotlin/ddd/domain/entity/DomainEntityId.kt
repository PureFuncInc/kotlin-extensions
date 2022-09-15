package net.purefunc.kotlin.ddd.domain.entity

import net.purefunc.kotlin.ddd.domain.vo.DomainVO

abstract class DomainEntityId<T : DomainEntityId<T>> : DomainVO() {
    abstract val uuid: Long
    abstract val createUser: String
    abstract val lastModifiedUser: String
    abstract val identity: Long
    abstract override val memo: String
    abstract fun create(uuid: Long): T
}
