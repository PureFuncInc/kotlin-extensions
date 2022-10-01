package net.purefunc.kotlin.arrow

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right

suspend fun <L : AppErr, R> Either<L, R?>.eitherNextNull(
    appErr: L,
    λ: suspend () -> Unit = {},
): Either<L, R> =
    flatMap {
        it.eitherNull(appErr, λ)
    }

suspend fun <L : AppErr, R> Either<L, R>.eitherNextTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): Either<L, R> =
    flatMap {
        it.eitherTrue(appErr, λ)
    }

suspend inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, R> =
    flatMap {
        it.eitherApply(appErr, printTrace, λ)
    }

suspend inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, T> =
    flatMap {
        it.eitherRun(appErr, printTrace, λ)
    }

inline fun <L : AppErr, R> Either<L, R>.eitherNextUnit(
    λ: () -> Either<L, Unit> = { Unit.right() },
): Either<L, Unit> = λ()
