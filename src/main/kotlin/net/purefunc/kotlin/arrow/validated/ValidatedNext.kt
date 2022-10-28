package net.purefunc.kotlin.arrow.validated

import arrow.core.Either
import arrow.core.Nel
import arrow.core.flatMap
import net.purefunc.kotlin.arrow.AppErr

typealias EitherNel<A, B> = Either<Nel<A>, B>

fun <L : AppErr, R> EitherNel<L, R?>.validNextNull(
    appErr: L,
    λ: () -> Unit = {},
): EitherNel<L, R> =
    flatMap {
        it
            .validNull(
                appErr = appErr,
                λ = λ,
            )
            .toValidatedNel()
            .toEither()
    }

suspend fun <L : AppErr, R> EitherNel<L, R>.validNextTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): EitherNel<L, R> =
    flatMap {
        it
            .validTrue(
                appErr = appErr,
                λ = λ,
            )
            .toValidatedNel()
            .toEither()
    }

suspend inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): EitherNel<L, R> =
    flatMap {
        it
            .validApply(
                appErr = appErr,
                printTrace = printTrace,
                λ = λ,
            )
            .toValidatedNel()
            .toEither()
    }

suspend inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): EitherNel<L, T> =
    flatMap {
        it
            .validRun(
                appErr = appErr,
                printTrace = printTrace,
                λ = λ,
            )
            .toValidatedNel()
            .toEither()
    }
