package net.purefunc.kotlin.arrow.validated

import arrow.core.Validated
import arrow.core.invalid
import arrow.core.toOption
import arrow.core.traverse
import arrow.core.valid
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import net.purefunc.kotlin.arrow.AppErr
import net.purefunc.kotlin.ext.Slf4j.Companion.log

fun <L : AppErr, R> R?.validNull(
    appErr: L,
    λ: () -> Unit = {},
): Validated<L, R> =
    toOption()
        .fold(
            ifEmpty = { appErr.invalid() },
            ifSome = {
                λ()
                it.valid()
            },
        )

suspend fun <L : AppErr, R> R.validTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): Validated<L, R> =
    takeIf { λ(it) }
        ?.run { appErr.invalid() }
        ?: run { valid() }

suspend inline fun <L : AppErr, reified R, T> R.validApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Validated<L, R> =
    try {
        λ()
        valid()
    } catch (tw: Throwable) {
        takeIf { printTrace }
            ?.run { log.error(tw.message, tw) }
            ?: run { log.error(tw.message) }
        appErr.invalid()
    }

suspend inline fun <L : AppErr, reified R, T> R.validRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Validated<L, T> =
    try {
        λ().valid()
    } catch (tw: Throwable) {
        takeIf { printTrace }
            ?.run { log.error(tw.message, tw) }
            ?: run { log.error(tw.message) }
        appErr.invalid()
    }

suspend fun <L : AppErr> List<Validated<L, *>>.validRunAll(
    ctx: CoroutineDispatcher = Dispatchers.IO,
): EitherNel<L, List<*>> =
    coroutineScope {
        this@validRunAll
            .map {
                async(ctx) {
                    it.suspendFuncWrapper()().toValidatedNel()
                }
            }
            .awaitAll()
            .traverse { it }
            .toEither()
    }

private fun <L : AppErr> Validated<L, *>.suspendFuncWrapper(): suspend () -> Validated<L, *> = { this }
