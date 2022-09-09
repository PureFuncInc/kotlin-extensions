package net.purefunc.kotlin.ddd.interfaces

data class CommandResponse<T>(

    val status: Int?,

    val headers: Map<String, String>?,

    val body: T?,
)
