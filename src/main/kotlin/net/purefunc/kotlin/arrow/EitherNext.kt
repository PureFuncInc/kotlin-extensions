package net.purefunc.kotlin.arrow

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right

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
