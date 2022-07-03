package net.purefunc.kotlin.ext

import arrow.core.Either

inline fun <reified A, B> Either<A, B>.isEitherLeft() =
    when (this) {
        is Either.Left -> value
        is Either.Right -> throw AssertionError()
    }

fun <A, B> Either<A, B>.isEitherRight() =
    when (this) {
        is Either.Left -> throw AssertionError()
        is Either.Right -> value
    }

fun <T> T?.catchErrWhenNull(tw: Throwable): Either<Throwable, T> = Either.Companion.catch { this ?: throw tw }

fun <T> Either<Throwable, T?>.flattenCatchErrWhenNull(tw: Throwable): Either<Throwable, T> =
    when (this) {
        is Either.Left -> this
        is Either.Right -> Either.Companion.catch {
            this.value ?: throw tw
        }
    }

suspend fun <T> T.catchErrWhenTrue(
    tw: Throwable,
    block: suspend (t: T) -> Boolean,
): Either<Throwable, T> =
    Either.Companion.catch {
        if (block.invoke(this)) throw tw
        else this
    }

suspend fun <T> Either<Throwable, T>.flattenCatchErrWhenTrue(
    tw: Throwable,
    block: suspend (t: T) -> Boolean,
): Either<Throwable, T> =
    when (this) {
        is Either.Left -> this
        is Either.Right -> Either.Companion.catch {
            if (block.invoke(this.value)) throw tw
            else this.value
        }
    }

suspend fun <T, R> T.catchErrWhenApply(
    tw: Throwable,
    block: suspend (t: T) -> R,
): Either<Throwable, T> =
    Either.Companion.catch {
        try {
            block.invoke(this)
            this
        } catch (_: Throwable) {
            throw tw
        }
    }

suspend fun <T, R> Either<Throwable, T>.flattenCatchErrWhenApply(
    tw: Throwable,
    block: suspend (t: T) -> R,
): Either<Throwable, T> =
    when (this) {
        is Either.Left -> this
        is Either.Right -> Either.Companion.catch {
            try {
                block.invoke(this.value)
                this.value
            } catch (_: Throwable) {
                throw tw
            }
        }
    }

suspend fun <T, R> T.catchErrWhenMap(
    tw: Throwable,
    block: suspend (t: T) -> R,
): Either<Throwable, R> =
    Either.Companion.catch {
        try {
            block.invoke(this)
        } catch (_: Throwable) {
            throw tw
        }
    }

suspend fun <T, R> Either<Throwable, T>.flattenCatchErrWhenMap(
    tw: Throwable,
    block: suspend (t: T) -> R,
): Either<Throwable, R> =
    when (this) {
        is Either.Left -> this
        is Either.Right -> Either.Companion.catch {
            try {
                block.invoke(this.value)
            } catch (_: Throwable) {
                throw tw
            }
        }
    }