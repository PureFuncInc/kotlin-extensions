package net.purefunc.kotlin.ddd.infrastructure.po

import net.purefunc.kotlin.ddd.infrastructure.enu.Deleted

abstract class BasePo {
    abstract val uuid: Long
    abstract val deleted: Deleted
    abstract val createDate: Long
    abstract val lastModifiedDate: Long
    abstract val createUser: String
    abstract val lastModifiedUser: String
    abstract val memo: String
}
