package net.purefunc.kotlin.ddd.interfaces

class CommandResponse<T> private constructor(
    var status: Int?,
    var headers: Map<String, String>?,
    var body: T?,
) {

    data class Builder<T>(
        var status: Int?,
        var headers: Map<String, String>?,
        var body: T?,
    ) {
        fun status(status: Int): Builder<T> = apply { this.status = status }
        fun headers(headers: Map<String, String>): Builder<T> = apply { this.headers = headers }
        fun body(t: T): CommandResponse<T> = CommandResponse(status, headers, body)
    }
}
