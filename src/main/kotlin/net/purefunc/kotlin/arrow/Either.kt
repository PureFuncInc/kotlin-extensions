package net.purefunc.kotlin.arrow

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import arrow.core.traverse
import net.purefunc.kotlin.ext.Slf4j.Companion.log

open class AppErr(
    val code: String,
    val message: String,
)

/**
 * Either Null
 *
 * @param L
 * @param R
 * @param appErr
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> R?.eitherNull(
    appErr: L,
    λ: suspend () -> Unit = {},
): Either<L, R> =
    toOption()
        .fold(
            ifEmpty = {
                appErr.left()
            },
            ifSome = {
                λ()
                it.right()
            },
        )

/**
 * Either True
 *
 * @param L
 * @param R
 * @param appErr
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> R.eitherTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): Either<L, R> =
    if (λ(this)) appErr.left()
    else right()

/**
 * Either Apply
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend inline fun <L : AppErr, reified R, T> R.eitherApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, R> =
    try {
        λ()
        right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

/**
 * Either Run
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend inline fun <L : AppErr, reified R, T> R.eitherRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, T> =
    try {
        λ().right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

/**
 * Either Null
 *
 * @param L
 * @param R
 * @param appErr
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> Either<L, R?>.eitherNextNull(
    appErr: L,
    λ: suspend () -> Unit = {},
): Either<L, R> =
    flatMap {
        it.eitherNull(appErr, λ)
    }

/**
 * Either True
 *
 * @param L
 * @param R
 * @param appErr
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> Either<L, R>.eitherNextTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): Either<L, R> =
    flatMap {
        it.eitherTrue(appErr, λ)
    }

/**
 * Either Apply
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, R> =
    flatMap {
        it.eitherApply(appErr, printTrace, λ)
    }

/**
 * Either Run
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, T> =
    flatMap {
        it.eitherRun(appErr, printTrace, λ)
    }

/**
 * Return Unit
 *
 * @param L
 * @param R
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, R> Either<L, R>.eitherNextUnit(
    λ: () -> Either<L, Unit> = { Unit.right() },
): Either<L, Unit> = λ()

/**
 * Flat Either Apply
 *
 * @param L
 * @param R
 * @param T
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend inline fun <L : AppErr, reified R, T> R.flatEitherApply(
    λ: suspend R.() -> Either<L, T>,
): Either<L, R> =
    run {
        λ()
        right()
    }

/**
 * Flat Either Run
 *
 * @param L
 * @param R
 * @param T
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend inline fun <L : AppErr, reified R, T> R.flatEitherRun(
    λ: suspend R.() -> Either<L, T>,
): Either<L, T> =
    run {
        λ()
    }

/**
 * Flat Either Apply
 *
 * @param L
 * @param R
 * @param T
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextApply(
    λ: suspend R.() -> Either<L, T>,
): Either<L, R> =
    flatMap {
        it.flatEitherApply(λ)
    }

/**
 * Flat Either Run
 *
 * @param L
 * @param R
 * @param T
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextRun(
    λ: suspend R.() -> Either<L, T>,
): Either<L, T> =
    flatMap {
        it.flatEitherRun(λ)
    }

/**
 * Zip All Eithers
 *
 * @param L
 * @param eithers
 *
 * @return
 */
fun <L : AppErr> zipAllEithers(
    vararg eithers: Either<L, *>,
): Either<L, List<*>> =
    eithers
        .toList()
        .traverse { it }
