package net.purefunc.kotlin

import arrow.core.Either
import arrow.core.continuations.either
import arrow.core.left
import arrow.core.right
import arrow.fx.coroutines.parTraverseEither
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import net.purefunc.kotlin.arrow.AppErr
import net.purefunc.kotlin.arrow.AssertErr
import net.purefunc.kotlin.arrow.either.eitherRun
import java.time.Instant
import kotlin.coroutines.CoroutineContext

data class User(val id: Int, val createdOn: String)

suspend fun getUserById(id: Int): Either<AppErr, User> =
    if (id == 4) {
        delay(1000)
        AssertErr.left()
    } else {
        delay(1000)
        User(id, Thread.currentThread().name).right()
    }

suspend fun main() {
    val aa: suspend () -> Either<AssertErr, String> = {
        delay(1000)
        mapOf("key" to "value").eitherRun(AssertErr) {
            Thread.currentThread().name
        }
    }

    val a = Instant.now().toEpochMilli()
    //sampleStart
    val res = listOf(1, 2, 3).parTraverseEither(Dispatchers.IO) { getUserById(it) }
    val res2 = listOf(1, 4, 2, 3).parTraverseEither(Dispatchers.IO) { getUserById(it) }
    val res4 = listOf(aa, aa, aa).runAll(Dispatchers.IO)
    //sampleEnd
    val b = Instant.now().toEpochMilli()
    println(res)
    println(res2)
    println(res4)
    println(b - a)
}

//suspend fun <A, B, E> Iterable<A>.parTraverseEither(
//    ctx: CoroutineContext = EmptyCoroutineContext,
//    f: suspend CoroutineScope.(A) -> Either<E, B>
//): Either<E, List<B>> =
//    either {
//        coroutineScope {
//            map { async(ctx) { f.invoke(this, it).bind() } }.awaitAll()
//        }
//    }

suspend fun List<suspend () -> Either<AppErr, *>>.runAll(
    ctx: CoroutineContext,
): Either<AppErr, List<*>> =
    either {
        coroutineScope {
            this@runAll.map {
                async(ctx) {
                    it().bind()
                }
            }.awaitAll()
        }
    }
