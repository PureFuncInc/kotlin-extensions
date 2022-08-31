package net.purefunc.kotlin.ddd.infrastructure.po

import net.purefunc.kotlin.ddd.infrastructure.enu.Deleted

abstract class BasePo {
    abstract val uuid: Long
    abstract var deleted: Deleted
    abstract val createDate: Long
    abstract var lastModifiedDate: Long
    abstract val createUser: String
    abstract var lastModifiedUser: String
    abstract var memo: String
}
