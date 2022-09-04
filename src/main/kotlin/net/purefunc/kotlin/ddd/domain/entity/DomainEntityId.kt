package net.purefunc.kotlin.ddd.domain.entity

import net.purefunc.kotlin.ddd.domain.vo.DomainVO

abstract class DomainEntityId : DomainVO() {
    abstract val identify: Int
    abstract val uuid: Long
    abstract override val memo: String
}
