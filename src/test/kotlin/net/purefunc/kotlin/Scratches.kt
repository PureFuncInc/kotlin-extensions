package net.purefunc.kotlin

import arrow.core.Either
import net.purefunc.kotlin.ext.NullErr
import net.purefunc.kotlin.ext.eitherCatchWhenRun

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
    val bar1: Bar =
        Foo(0, "", "")
            .run(foobar)

    val bar2: Either<NullErr, Bar> =
        Foo(0, "", "")
            .eitherCatchWhenRun(
                appErr = NullErr,
                block = foobar,
            )
}
