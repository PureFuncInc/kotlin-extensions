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

suspend fun <L : AppErr, R> R?.validNull(
    appErr: L,
    λ: suspend () -> Unit = {},
): Validated<L, R> =
    toOption()
        .fold(
            ifEmpty = {
                appErr.invalid()
            },
            ifSome = {
                λ()
                it.valid()
            },
        )

suspend fun <L : AppErr, R> R.validTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): Validated<L, R> =
    (if (λ(this)) appErr.invalid() else valid())

suspend inline fun <L : AppErr, reified R, T> R.validApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Validated<L, R> =
    try {
        λ()
        valid()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw)
        else log.error(tw.message)
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
        if (printTrace) log.error(tw.message, tw)
        else log.error(tw.message)
        appErr.invalid()
    }

suspend fun <L : AppErr> List<suspend () -> Validated<L, *>>.parallelRunAll(
    ctx: CoroutineDispatcher = Dispatchers.IO,
): EitherNel<L, List<*>> =
    coroutineScope {
        this@parallelRunAll
            .map { async(ctx) { it().toValidatedNel() } }
            .awaitAll()
            .traverse { it }
            .toEither()
    }
