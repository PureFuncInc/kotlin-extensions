package net.purefunc.kotlin

import arrow.core.Either
import net.purefunc.kotlin.arrow.Type1Err
import net.purefunc.kotlin.arrow.either.eitherRun

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

suspend fun main() {
    val bar1: Bar =
        Foo(0, "", "")
            .run(foobar)

    val bar2: Either<Type1Err, Bar> =
        Foo(0, "", "")
            .eitherRun(
                appErr = Type1Err,
                λ = foobar,
            )
}
