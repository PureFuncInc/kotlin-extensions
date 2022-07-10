package net.purefunc.kotlin.ext

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.Tuple4
import arrow.core.Tuple5
import arrow.core.Tuple6
import arrow.core.Tuple7
import arrow.core.Tuple8
import arrow.core.Tuple9
import arrow.core.Validated
import arrow.core.ValidatedNel
import arrow.core.flatMap
import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import arrow.core.zip

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

// catchErr Series
// when null
// when true
// when apply
// when run

fun <T> T?.catchErrWhenNull(tw: Throwable): Either<Throwable, T> = Either.Companion.catch { this ?: throw tw }

suspend fun <T> T.catchErrWhenTrue(
    tw: Throwable,
    block: suspend (t: T) -> Boolean,
): Either<Throwable, T> =
    Either.Companion.catch {
        if (block.invoke(this)) throw tw
        else this
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

fun <T> Either<Throwable, T?>.flatCatchErrWhenNull(tw: Throwable): Either<Throwable, T> =
    flatMap { t ->
        t.toOption()
            .fold(
                ifEmpty = { tw.left() },
                ifSome = { it.right() },
            )
    }

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

// validErr Series
// when null
// when true
// when apply
// when run

fun <T> T?.validErrWhenNull(tw: Throwable): ValidatedNel<Throwable, T> =
    Validated.Companion.catch { this ?: throw tw }.toValidatedNel()

suspend fun <T> T.validErrWhenTrue(
    tw: Throwable,
    block: suspend (t: T) -> Boolean,
): ValidatedNel<Throwable, T> =
    Validated.Companion.catch {
        if (block.invoke(this)) throw tw
        else this
    }.toValidatedNel()

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

// flatValidErr Series
// when null
// when true
// when apply
// when run

fun <T> EitherNel<Throwable, T?>.flatValidErrWhenNull(tw: Throwable): EitherNel<Throwable, T> =
    flatMap { t ->
        t.toOption()
            .fold(
                ifEmpty = { tw.left() },
                ifSome = { it.right() },
            )
            .toValidatedNel()
            .toEither()
    }

suspend fun <T> EitherNel<Throwable, T>.flatValidErrWhenTrue(
    tw: Throwable,
    block: suspend (t: T) -> Boolean,
): EitherNel<Throwable, T> =
    when (this) {
        is Either.Left -> this
        is Either.Right ->
            Validated.Companion.catch {
                if (block.invoke(this.value)) throw tw
                else this.value
            }.toValidatedNel().toEither()
    }

suspend fun <T, R> EitherNel<Throwable, T>.flatValidErrWhenApply(
    tw: Throwable,
    block: suspend (t: T) -> R,
): EitherNel<Throwable, T> =
    when (this) {
        is Either.Left -> this
        is Either.Right -> Validated.Companion.catch {
            try {
                block.invoke(this.value)
                this.value
            } catch (_: Throwable) {
                throw tw
            }
        }.toValidatedNel().toEither()
    }

suspend fun <T, R> EitherNel<Throwable, T>.flatValidErrWhenRun(
    tw: Throwable,
    block: suspend (t: T) -> R,
): EitherNel<Throwable, R> =
    when (this) {
        is Either.Left -> this
        is Either.Right -> Validated.Companion.catch {
            try {
                block.invoke(this.value)
            } catch (_: Throwable) {
                throw tw
            }
        }.toValidatedNel().toEither()
    }

typealias Tuple2<A, B> = Pair<A, B>
typealias Tuple3<A, B, C> = Triple<A, B, C>
typealias EitherNel<A, B> = Either<NonEmptyList<A>, B>

fun <A, B> flatValid2(
    a: ValidatedNel<Throwable, A>,
    b: ValidatedNel<Throwable, B>,
): EitherNel<Throwable, Tuple2<A, B>> =
    a.zip(b) { v1, v2 ->
        Tuple2(v1, v2)
    }.toEither()

fun <A, B, C> flatValid3(
    a: ValidatedNel<Throwable, A>,
    b: ValidatedNel<Throwable, B>,
    c: ValidatedNel<Throwable, C>,
): EitherNel<Throwable, Tuple3<A, B, C>> =
    a.zip(b, c) { v1, v2, v3 ->
        Tuple3(v1, v2, v3)
    }.toEither()

fun <A, B, C, D> flatValid4(
    a: ValidatedNel<Throwable, A>,
    b: ValidatedNel<Throwable, B>,
    c: ValidatedNel<Throwable, C>,
    d: ValidatedNel<Throwable, D>,
): EitherNel<Throwable, Tuple4<A, B, C, D>> =
    a.zip(b, c, d) { v1, v2, v3, v4 ->
        Tuple4(v1, v2, v3, v4)
    }.toEither()

fun <A, B, C, D, E> flatValid5(
    a: ValidatedNel<Throwable, A>,
    b: ValidatedNel<Throwable, B>,
    c: ValidatedNel<Throwable, C>,
    d: ValidatedNel<Throwable, D>,
    e: ValidatedNel<Throwable, E>,
): EitherNel<Throwable, Tuple5<A, B, C, D, E>> =
    a.zip(b, c, d, e) { v1, v2, v3, v4, v5 ->
        Tuple5(v1, v2, v3, v4, v5)
    }.toEither()

fun <A, B, C, D, E, F> flatValid6(
    a: ValidatedNel<Throwable, A>,
    b: ValidatedNel<Throwable, B>,
    c: ValidatedNel<Throwable, C>,
    d: ValidatedNel<Throwable, D>,
    e: ValidatedNel<Throwable, E>,
    f: ValidatedNel<Throwable, F>,
): EitherNel<Throwable, Tuple6<A, B, C, D, E, F>> =
    a.zip(b, c, d, e, f) { v1, v2, v3, v4, v5, v6 ->
        Tuple6(v1, v2, v3, v4, v5, v6)
    }.toEither()

fun <A, B, C, D, E, F, G> flatValid7(
    a: ValidatedNel<Throwable, A>,
    b: ValidatedNel<Throwable, B>,
    c: ValidatedNel<Throwable, C>,
    d: ValidatedNel<Throwable, D>,
    e: ValidatedNel<Throwable, E>,
    f: ValidatedNel<Throwable, F>,
    g: ValidatedNel<Throwable, G>,
): EitherNel<Throwable, Tuple7<A, B, C, D, E, F, G>> =
    a.zip(b, c, d, e, f, g) { v1, v2, v3, v4, v5, v6, v7 ->
        Tuple7(v1, v2, v3, v4, v5, v6, v7)
    }.toEither()

fun <A, B, C, D, E, F, G, H> flatValid8(
    a: ValidatedNel<Throwable, A>,
    b: ValidatedNel<Throwable, B>,
    c: ValidatedNel<Throwable, C>,
    d: ValidatedNel<Throwable, D>,
    e: ValidatedNel<Throwable, E>,
    f: ValidatedNel<Throwable, F>,
    g: ValidatedNel<Throwable, G>,
    h: ValidatedNel<Throwable, H>,
): EitherNel<Throwable, Tuple8<A, B, C, D, E, F, G, H>> =
    a.zip(b, c, d, e, f, g, h) { v1, v2, v3, v4, v5, v6, v7, v8 ->
        Tuple8(v1, v2, v3, v4, v5, v6, v7, v8)
    }.toEither()

fun <A, B, C, D, E, F, G, H, I> flatValid9(
    a: ValidatedNel<Throwable, A>,
    b: ValidatedNel<Throwable, B>,
    c: ValidatedNel<Throwable, C>,
    d: ValidatedNel<Throwable, D>,
    e: ValidatedNel<Throwable, E>,
    f: ValidatedNel<Throwable, F>,
    g: ValidatedNel<Throwable, G>,
    h: ValidatedNel<Throwable, H>,
    i: ValidatedNel<Throwable, I>,
): EitherNel<Throwable, Tuple9<A, B, C, D, E, F, G, H, I>> =
    a.zip(b, c, d, e, f, g, h, i) { v1, v2, v3, v4, v5, v6, v7, v8, v9 ->
        Tuple9(v1, v2, v3, v4, v5, v6, v7, v8, v9)
    }.toEither()
