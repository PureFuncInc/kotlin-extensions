package net.purefunc.kotlin.ext

import arrow.core.Either
import arrow.core.Nel
import arrow.core.ValidatedNel
import arrow.core.flatMap
import arrow.core.invalid
import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import arrow.core.traverse
import arrow.core.valid
import net.purefunc.kotlin.ext.Slf4j.Companion.log

open class AppErr(
    val code: String,
    val message: String,
)

suspend fun <L : AppErr, R> R?.catchErrWhenNull(
    appErr: L,
    block: suspend () -> Unit,
): Either<L, R> =
    toOption()
        .fold(
            ifEmpty = {
                appErr.left()
            },
            ifSome = {
                block.invoke()
                it.right()
            },
        )

suspend fun <L : AppErr, R> R.catchErrWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): Either<L, R> =
    if (block.invoke(this)) appErr.left()
    else right()

suspend fun <L : AppErr, R> R.catchErrWhenFalse(
    appErr: L,
    block: suspend (R) -> Boolean,
): Either<L, R> =
    if (!block.invoke(this)) appErr.left()
    else right()

inline fun <L : AppErr, reified R, T> R.catchErrWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): Either<L, R> =
    try {
        block.invoke(this)
        right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

inline fun <L : AppErr, reified R, T> R.catchErrWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): Either<L, T> =
    try {
        block.invoke(this).right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

suspend fun <L : AppErr, R> Either<L, R?>.flatCatchErrWhenNull(
    appErr: L,
    block: suspend () -> Unit,
): Either<L, R> =
    flatMap {
        it.catchErrWhenNull(appErr, block)
    }

suspend fun <L : AppErr, R> Either<L, R>.flatCatchErrWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): Either<L, R> =
    flatMap {
        it.catchErrWhenTrue(appErr, block)
    }

suspend fun <L : AppErr, R> Either<L, R>.flatCatchErrWhenFalse(
    appErr: L,
    block: suspend (R) -> Boolean,
): Either<L, R> =
    flatMap {
        it.catchErrWhenFalse(appErr, block)
    }

inline fun <L : AppErr, reified R, T> Either<L, R>.flatCatchErrWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): Either<L, R> =
    flatMap {
        it.catchErrWhenApply(appErr, printTrace, block)
    }

inline fun <L : AppErr, reified R, T> Either<L, R>.flatCatchErrWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): Either<L, T> =
    flatMap {
        it.catchErrWhenRun(appErr, printTrace, block)
    }

fun <L : AppErr> zipAllEither(
    vararg eithers: Either<L, *>,
): Either<L, List<*>> =
    eithers
        .toList()
        .traverse { it }

suspend fun <L : AppErr, R> R?.validErrWhenNull(
    appErr: L,
    block: suspend () -> Unit,
): ValidatedNel<L, R> =
    toOption()
        .fold(
            ifEmpty = {
                appErr.invalid()
            },
            ifSome = {
                block.invoke()
                it.valid()
            },
        ).toValidatedNel()

suspend fun <L : AppErr, R> R.validErrWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): ValidatedNel<L, R> =
    (if (block.invoke(this)) appErr.invalid() else valid()).toValidatedNel()

suspend fun <L : AppErr, R> R.validErrWhenFalse(
    appErr: L,
    block: suspend (R) -> Boolean,
): ValidatedNel<L, R> =
    (if (!block.invoke(this)) appErr.invalid() else valid()).toValidatedNel()

inline fun <L : AppErr, reified R, T> R.validErrWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): ValidatedNel<L, R> =
    try {
        block.invoke(this)
        valid()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.invalid()
    }.toValidatedNel()

inline fun <L : AppErr, reified R, T> R.validErrWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): ValidatedNel<L, T> =
    try {
        block.invoke(this).valid()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.invalid()
    }.toValidatedNel()

typealias EitherNel<A, B> = Either<Nel<A>, B>

suspend fun <L : AppErr, R> EitherNel<L, R?>.flatValidErrWhenNull(
    appErr: L,
    block: suspend () -> Unit,
): EitherNel<L, R> =
    flatMap {
        it.validErrWhenNull(appErr, block).toEither()
    }

suspend fun <L : AppErr, R> EitherNel<L, R>.flatValidErrWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): EitherNel<L, R> =
    flatMap {
        it.validErrWhenTrue(appErr, block).toEither()
    }

suspend fun <L : AppErr, R> EitherNel<L, R>.flatValidErrWhenFalse(
    appErr: L,
    block: suspend (R) -> Boolean,
): EitherNel<L, R> =
    flatMap {
        it.validErrWhenFalse(appErr, block).toEither()
    }

inline fun <L : AppErr, reified R, T> EitherNel<L, R>.flatValidErrWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): EitherNel<L, R> =
    flatMap {
        it.validErrWhenApply(appErr, printTrace, block).toEither()
    }

inline fun <L : AppErr, reified R, T> EitherNel<L, R>.flatValidErrWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): EitherNel<L, T> =
    flatMap {
        it.validErrWhenRun(appErr, printTrace, block).toEither()
    }

fun <L : AppErr> zipAllValid(
    vararg validateNels: ValidatedNel<L, *>,
): EitherNel<L, List<*>> =
    validateNels
        .toList()
        .traverse { it }
        .toEither()
