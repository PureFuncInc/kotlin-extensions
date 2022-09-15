package net.purefunc.kotlin.ddd.domain.entity

import net.purefunc.kotlin.ddd.domain.vo.DomainVO

abstract class DomainEntityId : DomainVO() {
    abstract val uuid: Long
    abstract val createUser: String
    abstract val lastModifiedUser: String
    abstract val identify: Int
    abstract override val memo: String
}
