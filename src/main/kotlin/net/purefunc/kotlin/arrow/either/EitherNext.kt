package net.purefunc.kotlin.arrow.either

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import net.purefunc.kotlin.arrow.AppErr

fun <L : AppErr, R> Either<L, R?>.eitherNextNull(
    appErr: L,
    λ: () -> Unit = {},
): Either<L, R> =
    flatMap {
        it.eitherNull(
            appErr = appErr,
            λ = λ,
        )
    }

suspend fun <L : AppErr, R> Either<L, R>.eitherNextTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): Either<L, R> =
    flatMap {
        it.eitherTrue(
            appErr = appErr,
            λ = λ,
        )
    }

suspend inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, R> =
    flatMap {
        it.eitherApply(
            appErr = appErr,
            printTrace = printTrace,
            λ = λ,
        )
    }

suspend inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, T> =
    flatMap {
        it.eitherRun(
            appErr = appErr,
            printTrace = printTrace,
            λ = λ,
        )
    }

inline fun <L : AppErr, R> Either<L, R>.eitherNextUnit(
    λ: () -> Unit = {},
): Either<L, Unit> =
    flatMap {
        λ().right()
    }
