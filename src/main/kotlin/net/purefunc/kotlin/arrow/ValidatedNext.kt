package net.purefunc.kotlin.arrow

import arrow.core.Either
import arrow.core.Nel
import arrow.core.flatMap

typealias EitherNel<A, B> = Either<Nel<A>, B>

/**
 * Valid Null
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
suspend fun <L : AppErr, R> EitherNel<L, R?>.validNextNull(
    appErr: L,
    λ: suspend () -> Unit = {},
): EitherNel<L, R> =
    flatMap {
        it.validNull(appErr, λ).toEither()
    }

/**
 * Valid True
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
suspend fun <L : AppErr, R> EitherNel<L, R>.validNextTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): EitherNel<L, R> =
    flatMap {
        it.validTrue(appErr, λ).toEither()
    }

/**
 * Valid Apply
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
suspend inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): EitherNel<L, R> =
    flatMap {
        it.validApply(appErr, printTrace, λ).toEither()
    }

/**
 * Valid Run
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
suspend inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): EitherNel<L, T> =
    flatMap {
        it.validRun(appErr, printTrace, λ).toEither()
    }