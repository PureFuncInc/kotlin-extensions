package net.purefunc.kotlin

import net.purefunc.kotlin.ext.NullErr
import net.purefunc.kotlin.ext.eitherCatchWhenLet

data class Foo(
    val id: Long,
    val key: String,
    val value: String,
)

data class Bar(
    val key: String,
    val value: String,
)

val foobar: (Foo) -> (Bar) =
    { foo ->
        Bar(foo.key, foo.value)
    }

fun main() {
    Foo(0, "", "").run(foobar)

    Foo(0, "", "").eitherCatchWhenLet(NullErr) { foobar }
}
