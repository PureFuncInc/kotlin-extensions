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

typealias Tuple2<A, B> = Pair<A, B>
typealias Tuple3<A, B, C> = Triple<A, B, C>
typealias EitherNel<A, B> = Either<NonEmptyList<A>, B>

fun <A, B> Tuple2<ValidatedNel<Throwable, A>, ValidatedNel<Throwable, B>>.validAll(): EitherNel<Throwable, Tuple2<A, B>> =
    first.zip(second) { v1, v2 -> Tuple2(v1, v2) }.toEither()

fun <A, B, C> Tuple3<ValidatedNel<Throwable, A>, ValidatedNel<Throwable, B>, ValidatedNel<Throwable, C>>.validAll(): EitherNel<Throwable, Tuple3<A, B, C>> =
    first.zip(second, third) { v1, v2, v3 -> Tuple3(v1, v2, v3) }.toEither()

fun <A, B, C, D> Tuple4<ValidatedNel<Throwable, A>, ValidatedNel<Throwable, B>, ValidatedNel<Throwable, C>, ValidatedNel<Throwable, D>>.validAll(): EitherNel<Throwable, Tuple4<A, B, C, D>> =
    first.zip(second, third, fourth) { v1, v2, v3, v4 -> Tuple4(v1, v2, v3, v4) }.toEither()

fun <A, B, C, D, E> Tuple5<ValidatedNel<Throwable, A>, ValidatedNel<Throwable, B>, ValidatedNel<Throwable, C>, ValidatedNel<Throwable, D>, ValidatedNel<Throwable, E>>.validAll(): EitherNel<Throwable, Tuple5<A, B, C, D, E>> =
    first.zip(second, third, fourth, fifth) { v1, v2, v3, v4, v5 -> Tuple5(v1, v2, v3, v4, v5) }.toEither()

fun <A, B, C, D, E, F> Tuple6<ValidatedNel<Throwable, A>, ValidatedNel<Throwable, B>, ValidatedNel<Throwable, C>, ValidatedNel<Throwable, D>, ValidatedNel<Throwable, E>, ValidatedNel<Throwable, F>>.validAll(): EitherNel<Throwable, Tuple6<A, B, C, D, E, F>> =
    first.zip(
        second,
        third,
        fourth,
        fifth,
        sixth
    ) { v1, v2, v3, v4, v5, v6 -> Tuple6(v1, v2, v3, v4, v5, v6) }.toEither()

fun <A, B, C, D, E, F, G> Tuple7<ValidatedNel<Throwable, A>, ValidatedNel<Throwable, B>, ValidatedNel<Throwable, C>, ValidatedNel<Throwable, D>, ValidatedNel<Throwable, E>, ValidatedNel<Throwable, F>, ValidatedNel<Throwable, G>>.validAll(): EitherNel<Throwable, Tuple7<A, B, C, D, E, F, G>> =
    first.zip(
        second,
        third,
        fourth,
        fifth,
        sixth,
        seventh
    ) { v1, v2, v3, v4, v5, v6, v7 -> Tuple7(v1, v2, v3, v4, v5, v6, v7) }.toEither()

fun <A, B, C, D, E, F, G, H> Tuple8<ValidatedNel<Throwable, A>, ValidatedNel<Throwable, B>, ValidatedNel<Throwable, C>, ValidatedNel<Throwable, D>, ValidatedNel<Throwable, E>, ValidatedNel<Throwable, F>, ValidatedNel<Throwable, G>, ValidatedNel<Throwable, H>>.validAll(): EitherNel<Throwable, Tuple8<A, B, C, D, E, F, G, H>> =
    first.zip(
        second,
        third,
        fourth,
        fifth,
        sixth,
        seventh,
        eighth
    ) { v1, v2, v3, v4, v5, v6, v7, v8 -> Tuple8(v1, v2, v3, v4, v5, v6, v7, v8) }.toEither()

fun <A, B, C, D, E, F, G, H, I> Tuple9<ValidatedNel<Throwable, A>, ValidatedNel<Throwable, B>, ValidatedNel<Throwable, C>, ValidatedNel<Throwable, D>, ValidatedNel<Throwable, E>, ValidatedNel<Throwable, F>, ValidatedNel<Throwable, G>, ValidatedNel<Throwable, H>, ValidatedNel<Throwable, I>>.validAll(): EitherNel<Throwable, Tuple9<A, B, C, D, E, F, G, H, I>> =
    first.zip(
        second,
        third,
        fourth,
        fifth,
        sixth,
        seventh,
        eighth,
        ninth
    ) { v1, v2, v3, v4, v5, v6, v7, v8, v9 -> Tuple9(v1, v2, v3, v4, v5, v6, v7, v8, v9) }.toEither()
