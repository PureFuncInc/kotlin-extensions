package net.purefunc.kotlin.ext

import arrow.core.Either
import arrow.core.Validated
import arrow.core.ValidatedNel
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import arrow.core.toOption

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

fun <T> T?.validErrWhenNull(tw: Throwable): ValidatedNel<Throwable, T> =
    Validated.Companion.catch { this ?: throw tw }.toValidatedNel()

fun <T> Either<Throwable, T?>.flatCatchErrWhenNull(tw: Throwable): Either<Throwable, T> =
    flatMap { t ->
        t.toOption()
            .fold(
                ifEmpty = { tw.left() },
                ifSome = { it.right() },
            )
    }

suspend fun <T> T.catchErrWhenTrue(
    tw: Throwable,
    block: suspend (t: T) -> Boolean,
): Either<Throwable, T> =
    Either.Companion.catch {
        if (block.invoke(this)) throw tw
        else this
    }

suspend fun <T> T.validErrWhenTrue(
    tw: Throwable,
    block: suspend (t: T) -> Boolean,
): ValidatedNel<Throwable, T> =
    Validated.Companion.catch {
        if (block.invoke(this)) throw tw
        else this
    }.toValidatedNel()

suspend fun <T> Either<Throwable, T>.flatCatchErrWhenTrue(
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

suspend fun <T, R> T.validErrWhenApply(
    tw: Throwable,
    block: suspend (t: T) -> R,
): ValidatedNel<Throwable, T> =
    Validated.Companion.catch {
        try {
            block.invoke(this)
            this
        } catch (_: Throwable) {
            throw tw
        }
    }.toValidatedNel()

suspend fun <T, R> Either<Throwable, T>.flatCatchErrWhenApply(
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

suspend fun <T, R> T.catchErrWhenRun(
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

suspend fun <T, R> T.validErrWhenRun(
    tw: Throwable,
    block: suspend (t: T) -> R,
): ValidatedNel<Throwable, R> =
    Validated.Companion.catch {
        try {
            block.invoke(this)
        } catch (_: Throwable) {
            throw tw
        }
    }.toValidatedNel()

suspend fun <T, R> Either<Throwable, T>.flatCatchErrWhenRun(
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
