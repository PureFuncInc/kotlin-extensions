package net.purefunc.kotlin.ext

import arrow.core.Either
import arrow.core.NonEmptyList
import arrow.core.Tuple4
import arrow.core.Tuple5
import arrow.core.Tuple6
import arrow.core.Tuple7
import arrow.core.Tuple8
import arrow.core.Tuple9
import arrow.core.ValidatedNel
import arrow.core.flatMap
import arrow.core.invalid
import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import arrow.core.valid
import arrow.core.zip
import net.purefunc.kotlin.ext.Slf4j.Companion.log

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

open class CustomErr(
    val code: String,
    val message: String,
)

fun <T> T?.catchErrWhenNull(
    customErr: CustomErr
): Either<CustomErr, T> =
    toOption()
        .fold(
            ifEmpty = { customErr.left() },
            ifSome = { it.right() },
        )

suspend fun <T> T.catchErrWhenTrue(
    customErr: CustomErr,
    block: suspend (t: T) -> Boolean,
): Either<CustomErr, T> =
    if (block.invoke(this)) customErr.left()
    else this.right()

suspend inline fun <reified T, R> T.catchErrWhenApply(
    customErr: CustomErr,
    printTrace: Boolean = false,
    block: suspend (t: T) -> R,
): Either<CustomErr, T> =
    try {
        block.invoke(this)
        this.right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        customErr.left()
    }

suspend inline fun <reified T, R> T.catchErrWhenRun(
    customErr: CustomErr,
    printTrace: Boolean = false,
    block: suspend (t: T) -> R,
): Either<CustomErr, R> =
    try {
        block.invoke(this).right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        customErr.left()
    }

fun <T> Either<CustomErr, T?>.flatCatchErrWhenNull(
    customErr: CustomErr,
): Either<CustomErr, T> =
    flatMap { t ->
        t.toOption()
            .fold(
                ifEmpty = { customErr.left() },
                ifSome = { it.right() },
            )
    }

suspend fun <T> Either<CustomErr, T>.flatCatchErrWhenTrue(
    customErr: CustomErr,
    block: suspend (t: T) -> Boolean,
): Either<CustomErr, T> =
    when (this) {
        is Either.Left -> this
        is Either.Right ->
            if (block.invoke(this.value)) customErr.left()
            else this.value.right()
    }

suspend fun <T, R> Either<CustomErr, T>.flatCatchErrWhenApply(
    customErr: CustomErr,
    printTrace: Boolean = false,
    block: suspend (t: T) -> R,
): Either<CustomErr, T> =
    when (this) {
        is Either.Left -> this
        is Either.Right ->
            try {
                block.invoke(this.value)
                this.value.right()
            } catch (tw: Throwable) {
                if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
                customErr.left()
            }
    }

suspend fun <T, R> Either<CustomErr, T>.flatCatchErrWhenRun(
    customErr: CustomErr,
    printTrace: Boolean = false,
    block: suspend (t: T) -> R,
): Either<CustomErr, R> =
    when (this) {
        is Either.Left -> this
        is Either.Right ->
            try {
                block.invoke(this.value).right()
            } catch (tw: Throwable) {
                if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
                customErr.left()
            }
    }

fun <T> T?.validErrWhenNull(
    customErr: CustomErr,
): ValidatedNel<CustomErr, T> =
    toOption()
        .fold(
            ifEmpty = { customErr.invalid() },
            ifSome = { it.valid() },
        )
        .toValidatedNel()

suspend fun <T> T.validErrWhenTrue(
    customErr: CustomErr,
    block: suspend (t: T) -> Boolean,
): ValidatedNel<CustomErr, T> =
    run {
        if (block.invoke(this)) customErr.invalid()
        else this.valid()
    }.toValidatedNel()

suspend inline fun <reified T, R> T.validErrWhenApply(
    customErr: CustomErr,
    printTrace: Boolean = false,
    block: suspend (t: T) -> R,
): ValidatedNel<CustomErr, T> =
    run {
        try {
            block.invoke(this)
            this.valid()
        } catch (tw: Throwable) {
            if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
            customErr.invalid()
        }
    }.toValidatedNel()

suspend inline fun <reified T, R> T.validErrWhenRun(
    customErr: CustomErr,
    printTrace: Boolean = false,
    block: suspend (t: T) -> R,
): ValidatedNel<CustomErr, R> =
    run {
        try {
            block.invoke(this).valid()
        } catch (tw: Throwable) {
            if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
            customErr.invalid()
        }
    }.toValidatedNel()

typealias Tuple2<A, B> = Pair<A, B>
typealias Tuple3<A, B, C> = Triple<A, B, C>
typealias EitherNel<A, B> = Either<NonEmptyList<A>, B>

fun <T> EitherNel<CustomErr, T?>.flatValidErrWhenNull(
    customErr: CustomErr,
): EitherNel<CustomErr, T> =
    flatMap { t ->
        t.toOption()
            .fold(
                ifEmpty = { customErr.invalid() },
                ifSome = { it.valid() },
            )
            .toValidatedNel()
            .toEither()
    }

suspend fun <T> EitherNel<CustomErr, T>.flatValidErrWhenTrue(
    customErr: CustomErr,
    block: suspend (t: T) -> Boolean,
): EitherNel<CustomErr, T> =
    when (this) {
        is Either.Left -> this
        is Either.Right ->
            run {
                if (block.invoke(this.value)) customErr.invalid()
                else this.value.valid()
            }.toValidatedNel().toEither()
    }

suspend fun <T, R> EitherNel<CustomErr, T>.flatValidErrWhenApply(
    customErr: CustomErr,
    printTrace: Boolean = false,
    block: suspend (t: T) -> R,
): EitherNel<CustomErr, T> =
    when (this) {
        is Either.Left -> this
        is Either.Right ->
            run {
                try {
                    block.invoke(this.value)
                    this.value.valid()
                } catch (tw: Throwable) {
                    log.error(tw.message)
                    customErr.invalid()
                }
            }.toValidatedNel().toEither()
    }

suspend fun <T, R> EitherNel<CustomErr, T>.flatValidErrWhenRun(
    customErr: CustomErr,
    printTrace: Boolean = false,
    block: suspend (t: T) -> R,
): EitherNel<CustomErr, R> =
    when (this) {
        is Either.Left -> this
        is Either.Right ->
            run {
                try {
                    block.invoke(this.value).valid()
                } catch (tw: Throwable) {
                    log.error(tw.message)
                    customErr.invalid()
                }
            }.toValidatedNel().toEither()
    }

fun <A, B> flatValid2(
    a: ValidatedNel<CustomErr, A>,
    b: ValidatedNel<CustomErr, B>,
): EitherNel<CustomErr, Tuple2<A, B>> =
    a.zip(b) { v1, v2 ->
        Tuple2(v1, v2)
    }.toEither()

fun <A, B, C> flatValid3(
    a: ValidatedNel<CustomErr, A>,
    b: ValidatedNel<CustomErr, B>,
    c: ValidatedNel<CustomErr, C>,
): EitherNel<CustomErr, Tuple3<A, B, C>> =
    a.zip(b, c) { v1, v2, v3 ->
        Tuple3(v1, v2, v3)
    }.toEither()

fun <A, B, C, D> flatValid4(
    a: ValidatedNel<CustomErr, A>,
    b: ValidatedNel<CustomErr, B>,
    c: ValidatedNel<CustomErr, C>,
    d: ValidatedNel<CustomErr, D>,
): EitherNel<CustomErr, Tuple4<A, B, C, D>> =
    a.zip(b, c, d) { v1, v2, v3, v4 ->
        Tuple4(v1, v2, v3, v4)
    }.toEither()

fun <A, B, C, D, E> flatValid5(
    a: ValidatedNel<CustomErr, A>,
    b: ValidatedNel<CustomErr, B>,
    c: ValidatedNel<CustomErr, C>,
    d: ValidatedNel<CustomErr, D>,
    e: ValidatedNel<CustomErr, E>,
): EitherNel<CustomErr, Tuple5<A, B, C, D, E>> =
    a.zip(b, c, d, e) { v1, v2, v3, v4, v5 ->
        Tuple5(v1, v2, v3, v4, v5)
    }.toEither()

fun <A, B, C, D, E, F> flatValid6(
    a: ValidatedNel<CustomErr, A>,
    b: ValidatedNel<CustomErr, B>,
    c: ValidatedNel<CustomErr, C>,
    d: ValidatedNel<CustomErr, D>,
    e: ValidatedNel<CustomErr, E>,
    f: ValidatedNel<CustomErr, F>,
): EitherNel<CustomErr, Tuple6<A, B, C, D, E, F>> =
    a.zip(b, c, d, e, f) { v1, v2, v3, v4, v5, v6 ->
        Tuple6(v1, v2, v3, v4, v5, v6)
    }.toEither()

fun <A, B, C, D, E, F, G> flatValid7(
    a: ValidatedNel<CustomErr, A>,
    b: ValidatedNel<CustomErr, B>,
    c: ValidatedNel<CustomErr, C>,
    d: ValidatedNel<CustomErr, D>,
    e: ValidatedNel<CustomErr, E>,
    f: ValidatedNel<CustomErr, F>,
    g: ValidatedNel<CustomErr, G>,
): EitherNel<CustomErr, Tuple7<A, B, C, D, E, F, G>> =
    a.zip(b, c, d, e, f, g) { v1, v2, v3, v4, v5, v6, v7 ->
        Tuple7(v1, v2, v3, v4, v5, v6, v7)
    }.toEither()

fun <A, B, C, D, E, F, G, H> flatValid8(
    a: ValidatedNel<CustomErr, A>,
    b: ValidatedNel<CustomErr, B>,
    c: ValidatedNel<CustomErr, C>,
    d: ValidatedNel<CustomErr, D>,
    e: ValidatedNel<CustomErr, E>,
    f: ValidatedNel<CustomErr, F>,
    g: ValidatedNel<CustomErr, G>,
    h: ValidatedNel<CustomErr, H>,
): EitherNel<CustomErr, Tuple8<A, B, C, D, E, F, G, H>> =
    a.zip(b, c, d, e, f, g, h) { v1, v2, v3, v4, v5, v6, v7, v8 ->
        Tuple8(v1, v2, v3, v4, v5, v6, v7, v8)
    }.toEither()

fun <A, B, C, D, E, F, G, H, I> flatValid9(
    a: ValidatedNel<CustomErr, A>,
    b: ValidatedNel<CustomErr, B>,
    c: ValidatedNel<CustomErr, C>,
    d: ValidatedNel<CustomErr, D>,
    e: ValidatedNel<CustomErr, E>,
    f: ValidatedNel<CustomErr, F>,
    g: ValidatedNel<CustomErr, G>,
    h: ValidatedNel<CustomErr, H>,
    i: ValidatedNel<CustomErr, I>,
): EitherNel<CustomErr, Tuple9<A, B, C, D, E, F, G, H, I>> =
    a.zip(b, c, d, e, f, g, h, i) { v1, v2, v3, v4, v5, v6, v7, v8, v9 ->
        Tuple9(v1, v2, v3, v4, v5, v6, v7, v8, v9)
    }.toEither()
