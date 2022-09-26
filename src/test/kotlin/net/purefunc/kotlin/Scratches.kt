package net.purefunc.kotlin

import arrow.core.Either
import kotlinx.coroutines.runBlocking
import net.purefunc.kotlin.ext.NullErr
import net.purefunc.kotlin.ext.eitherRun

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
    runBlocking {
        val bar1: Bar =
            Foo(0, "", "")
                .run(foobar)

        val bar2: Either<NullErr, Bar> =
            Foo(0, "", "")
                .eitherRun(
                    appErr = NullErr,
                    λ = foobar,
                )
    }
}
