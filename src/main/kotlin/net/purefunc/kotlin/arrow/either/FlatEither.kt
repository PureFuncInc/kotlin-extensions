package net.purefunc.kotlin.arrow.either

import arrow.core.Either
import arrow.core.flatMap
import arrow.core.right
import net.purefunc.kotlin.arrow.AppErr

suspend inline fun <L : AppErr, reified R, T> R.flatEitherApply(
    λ: suspend R.() -> Either<L, T>,
): Either<L, R> =
    run {
        λ()
        right()
    }

suspend inline fun <L : AppErr, reified R, T> R.flatEitherRun(
    λ: suspend R.() -> Either<L, T>,
): Either<L, T> =
    run {
        λ()
    }

suspend inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextApply(
    λ: suspend R.() -> Either<L, T>,
): Either<L, R> =
    flatMap {
        it.flatEitherApply(λ)
    }

suspend inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextRun(
    λ: suspend R.() -> Either<L, T>,
): Either<L, T> =
    flatMap {
        it.flatEitherRun(λ)
    }
