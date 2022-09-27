package net.purefunc.kotlin.arrow

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right

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
