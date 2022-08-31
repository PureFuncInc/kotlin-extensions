package net.purefunc.kotlin.ddd.domain.entity

import net.purefunc.kotlin.ddd.domain.vo.DomainValueObject

abstract class DomainEntityId : DomainValueObject() {
    abstract val identify: Int
    abstract val uuid: Long
    abstract override val memo: String
}
