package net.purefunc.kotlin.ddd.interfaces

data class CommandResponse<T>(
    val status: Int,
    val headers: Map<String, String>,
    val body: T?,
) {

    fun addHeader(
        key: String,
        value: String,
    ): CommandResponse<T> =
        copy(
            headers = headers
                .toMutableMap()
                .apply { put(key, value) }
        )
}
