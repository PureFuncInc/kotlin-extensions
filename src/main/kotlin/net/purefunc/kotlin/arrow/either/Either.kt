package net.purefunc.kotlin.arrow.either

import arrow.core.Either
import arrow.core.continuations.either
import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import net.purefunc.kotlin.arrow.AppErr
import net.purefunc.kotlin.ext.Slf4j.Companion.log

fun <L : AppErr, R> R?.eitherNull(
    appErr: L,
    λ: () -> Unit = {},
): Either<L, R> =
    toOption()
        .fold(
            ifEmpty = { appErr.left() },
            ifSome = {
                λ()
                it.right()
            },
        )

suspend fun <L : AppErr, R> R.eitherTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): Either<L, R> =
    takeIf { λ(it) }
        ?.run { appErr.left() }
        ?: run { right() }

suspend inline fun <L : AppErr, reified R, T> R.eitherApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, R> =
    try {
        λ()
        right()
    } catch (tw: Throwable) {
        takeIf { printTrace }
            ?.run { log.error(tw.message, tw) }
            ?: run { log.error(tw.message) }
        appErr.left()
    }

suspend inline fun <L : AppErr, reified R, T> R.eitherRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, T> =
    try {
        λ().right()
    } catch (tw: Throwable) {
        takeIf { printTrace }
            ?.run { log.error(tw.message, tw) }
            ?: run { log.error(tw.message) }
        appErr.left()
    }

suspend fun <L : AppErr> List<Either<L, *>>.eitherRunAll(
    ctx: CoroutineDispatcher = Dispatchers.IO,
): Either<L, List<*>> =
    either {
        coroutineScope {
            this@eitherRunAll
                .map {
                    async(ctx) {
                        it.suspendFuncWrapper()().bind()
                    }
                }
                .awaitAll()
        }
    }

private fun <L : AppErr> Either<L, *>.suspendFuncWrapper(): suspend () -> Either<L, *> = { this }
