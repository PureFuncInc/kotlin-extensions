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

/*
 *     eitherCatchWhenRun -> in R           , block (R) -> T           , out Either<L, T>
 *      eitherNextWhenRun -> in Either<L, R>, block (R) -> T           , out Either<L, T>
 * flatEitherCatchWhenRun -> in R           , block (R) -> Either<L, T>, out Either<L, T>
 *  flatEitherNextWhenRun -> in Either<L, R>, block (R) -> Either<L, T>, out Either<L, T>
 *
 *   validCatchErrWhenRun -> in R           , block (R) -> T           , out ValidatedNel<L, T>
 *    validNextErrWhenRun -> in Either<L, R>, block (R) -> T           , out ValidatedNel<L, T>
 */

suspend fun <L : AppErr, R> R?.eitherCatchWhenNull(
    appErr: L,
    block: suspend () -> Unit = {},
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

suspend fun <L : AppErr, R> R.eitherCatchWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): Either<L, R> =
    if (block.invoke(this)) appErr.left()
    else right()

suspend fun <L : AppErr, R> R.eitherCatchWhenFalse(
    appErr: L,
    block: suspend (R) -> Boolean,
): Either<L, R> =
    if (!block.invoke(this)) appErr.left()
    else right()

inline fun <L : AppErr, reified R, T> R.eitherCatchWhenApply(
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

inline fun <L : AppErr, reified R, T> R.eitherCatchWhenRun(
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

suspend fun <L : AppErr, R> Either<L, R?>.eitherNextWhenNull(
    appErr: L,
    block: suspend () -> Unit = {},
): Either<L, R> =
    flatMap {
        it.eitherCatchWhenNull(appErr, block)
    }

suspend fun <L : AppErr, R> Either<L, R>.eitherNextWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): Either<L, R> =
    flatMap {
        it.eitherCatchWhenTrue(appErr, block)
    }

suspend fun <L : AppErr, R> Either<L, R>.eitherNextWhenFalse(
    appErr: L,
    block: suspend (R) -> Boolean,
): Either<L, R> =
    flatMap {
        it.eitherCatchWhenFalse(appErr, block)
    }

inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): Either<L, R> =
    flatMap {
        it.eitherCatchWhenApply(appErr, printTrace, block)
    }

inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): Either<L, T> =
    flatMap {
        it.eitherCatchWhenRun(appErr, printTrace, block)
    }

suspend fun <L : AppErr, R> R.flatEitherCatchWhenTrue(
    appErr: L,
    block: suspend (R) -> Either<L, Boolean>,
): Either<L, R> =
    block
        .invoke(this)
        .flatMap { bool ->
            if (bool) return@flatMap appErr.left()
            else this.right()
        }

suspend fun <L : AppErr, R> R.flatEitherCatchWhenFalse(
    appErr: L,
    block: suspend (R) -> Either<L, Boolean>,
): Either<L, R> =
    block
        .invoke(this)
        .flatMap { bool ->
            if (!bool) return@flatMap appErr.left()
            else this.right()
        }

inline fun <L : AppErr, reified R, T> R.flatEitherCatchWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> Either<L, T>,
): Either<L, R> =
    try {
        block
            .invoke(this)
            .map { _ -> this }
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

inline fun <L : AppErr, reified R, T> R.flatEitherCatchWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> Either<L, T>,
): Either<L, T> =
    try {
        block.invoke(this)
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

suspend fun <L : AppErr, R> Either<L, R>.flatEitherNextWhenTrue(
    appErr: L,
    block: suspend (R) -> Either<L, Boolean>,
): Either<L, R> =
    flatMap {
        it.flatEitherCatchWhenTrue(appErr, block)
    }

suspend fun <L : AppErr, R> Either<L, R>.flatEitherNextWhenFalse(
    appErr: L,
    block: suspend (R) -> Either<L, Boolean>,
): Either<L, R> =
    flatMap {
        it.flatEitherCatchWhenFalse(appErr, block)
    }

inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> Either<L, T>,
): Either<L, R> =
    flatMap {
        it.flatEitherCatchWhenApply(appErr, printTrace, block)
    }

inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> Either<L, T>,
): Either<L, T> =
    flatMap {
        it.flatEitherCatchWhenRun(appErr, printTrace, block)
    }

fun <L : AppErr> zipAllEithers(
    vararg eithers: Either<L, *>,
): Either<L, List<*>> =
    eithers
        .toList()
        .traverse { it }

suspend fun <L : AppErr, R> R?.validCatchWhenNull(
    appErr: L,
    block: suspend () -> Unit = {},
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

suspend fun <L : AppErr, R> R.validCatchWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): ValidatedNel<L, R> =
    (if (block.invoke(this)) appErr.invalid() else valid()).toValidatedNel()

suspend fun <L : AppErr, R> R.validCatchWhenFalse(
    appErr: L,
    block: suspend (R) -> Boolean,
): ValidatedNel<L, R> =
    (if (!block.invoke(this)) appErr.invalid() else valid()).toValidatedNel()

inline fun <L : AppErr, reified R, T> R.validCatchWhenApply(
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

inline fun <L : AppErr, reified R, T> R.validCatchWhenRun(
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

suspend fun <L : AppErr, R> EitherNel<L, R?>.validNextWhenNull(
    appErr: L,
    block: suspend () -> Unit = {},
): EitherNel<L, R> =
    flatMap {
        it.validCatchWhenNull(appErr, block).toEither()
    }

suspend fun <L : AppErr, R> EitherNel<L, R>.validNextWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): EitherNel<L, R> =
    flatMap {
        it.validCatchWhenTrue(appErr, block).toEither()
    }

suspend fun <L : AppErr, R> EitherNel<L, R>.validNextWhenFalse(
    appErr: L,
    block: suspend (R) -> Boolean,
): EitherNel<L, R> =
    flatMap {
        it.validCatchWhenFalse(appErr, block).toEither()
    }

inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): EitherNel<L, R> =
    flatMap {
        it.validCatchWhenApply(appErr, printTrace, block).toEither()
    }

inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): EitherNel<L, T> =
    flatMap {
        it.validCatchWhenRun(appErr, printTrace, block).toEither()
    }

fun <L : AppErr> zipAllValids(
    vararg validateNels: ValidatedNel<L, *>,
): EitherNel<L, List<*>> =
    validateNels
        .toList()
        .traverse { it }
        .toEither()
