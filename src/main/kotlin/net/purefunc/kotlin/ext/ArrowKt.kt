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
 * next represent extended Either<L, R>
 * flat represent block return Either<L, R>
 *
 *     eitherCatchWhenRun -> receiver R           , block (R) -> T           , return Either<L, T>
 *      eitherNextWhenRun -> receiver Either<L, R>, block (R) -> T           , return Either<L, T>
 * flatEitherCatchWhenRun -> receiver R           , block (R) -> Either<L, T>, return Either<L, T>
 *  flatEitherNextWhenRun -> receiver Either<L, R>, block (R) -> Either<L, T>, return Either<L, T>
 *
 *   validCatchErrWhenRun -> receiver R           , block (R) -> T           , return ValidatedNel<L, T>
 *    validNextErrWhenRun -> receiver Either<L, R>, block (R) -> T           , return ValidatedNel<L, T>
 */

/**
 * Either Catch When Null
 *
 * @param L
 * @param R
 * @param appErr
 * @param block
 *
 * @receiver
 *
 * @return
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
                block()
                it.right()
            },
        )

/**
 * Either Catch When True
 *
 * @param L
 * @param R
 * @param appErr
 * @param block
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> R.eitherCatchWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): Either<L, R> =
    if (block(this)) appErr.left()
    else right()

/**
 * Either Catch When Apply
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.eitherCatchWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: R.() -> T,
): Either<L, R> =
    try {
        block()
        right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

/**
 * Either Catch When Also
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.eitherCatchWhenAlso(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): Either<L, R> =
    try {
        block(this)
        right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

/**
 * Either Catch When Run
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.eitherCatchWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: R.() -> T,
): Either<L, T> =
    try {
        block().right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

/**
 * Either Catch When Let
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.eitherCatchWhenLet(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): Either<L, T> =
    try {
        block(this).right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

/**
 * Either Next When Null
 *
 * @param L
 * @param R
 * @param appErr
 * @param block
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> Either<L, R?>.eitherNextWhenNull(
    appErr: L,
    block: suspend () -> Unit = {},
): Either<L, R> =
    flatMap {
        it.eitherCatchWhenNull(appErr, block)
    }

/**
 * Either Next When True
 *
 * @param L
 * @param R
 * @param appErr
 * @param block
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> Either<L, R>.eitherNextWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): Either<L, R> =
    flatMap {
        it.eitherCatchWhenTrue(appErr, block)
    }

/**
 * Either Next When Apply
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: R.() -> T,
): Either<L, R> =
    flatMap {
        it.eitherCatchWhenApply(appErr, printTrace, block)
    }

/**
 * Either Next When Also
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextWhenAlso(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): Either<L, R> =
    flatMap {
        it.eitherCatchWhenAlso(appErr, printTrace, block)
    }

/**
 * Either Next When Run
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: R.() -> T,
): Either<L, T> =
    flatMap {
        it.eitherCatchWhenRun(appErr, printTrace, block)
    }

/**
 * Either Next When Let
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextWhenLet(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): Either<L, T> =
    flatMap {
        it.eitherCatchWhenLet(appErr, printTrace, block)
    }

/**
 * Return Unit
 *
 * @param L
 * @param R
 * @param printR
 *
 * @return
 */
inline fun <L : AppErr, reified R> Either<L, R>.returnUnit(
    printR: Boolean = false,
): Either<L, Unit> =
    flatMap {
        if (printR) log.error(it.toString())
        Unit.right()
    }

/**
 * Flat Either Catch When Apply
 *
 * @param L
 * @param R
 * @param T
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.flatEitherCatchWhenApply(
    block: R.() -> T,
): Either<L, R> = run {
    block()
    right()
}

/**
 * Flat Either Catch When Also
 *
 * @param L
 * @param R
 * @param T
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.flatEitherCatchWhenAlso(
    block: (R) -> T,
): Either<L, R> = run {
    block(this)
    right()
}

/**
 * Flat Either Catch When Run
 *
 * @param L
 * @param R
 * @param T
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.flatEitherCatchWhenRun(
    block: R.() -> Either<L, T>,
): Either<L, T> = block()

/**
 * Flat Either Catch When Let
 *
 * @param L
 * @param R
 * @param T
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.flatEitherCatchWhenLet(
    block: (R) -> Either<L, T>,
): Either<L, T> = block(this)

/**
 * Flat Either Next When Apply
 *
 * @param L
 * @param R
 * @param T
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextWhenApply(
    block: R.() -> Either<L, T>,
): Either<L, R> =
    flatMap {
        it.flatEitherCatchWhenApply(block)
    }

/**
 * Flat Either Next When Also
 *
 * @param L
 * @param R
 * @param T
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextWhenAlso(
    block: (R) -> Either<L, T>,
): Either<L, R> =
    flatMap {
        it.flatEitherCatchWhenAlso(block)
    }

/**
 * Flat Either Next When Run
 *
 * @param L
 * @param R
 * @param T
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextWhenRun(
    block: R.() -> Either<L, T>,
): Either<L, T> =
    flatMap {
        it.flatEitherCatchWhenRun(block)
    }

/**
 * Flat Either Next When Let
 *
 * @param L
 * @param R
 * @param T
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextWhenLet(
    block: (R) -> Either<L, T>,
): Either<L, T> =
    flatMap {
        it.flatEitherCatchWhenLet(block)
    }

/**
 * Zip All Eithers
 *
 * @param L
 * @param eithers
 *
 * @return
 */
fun <L : AppErr> zipAllEithers(
    vararg eithers: Either<L, *>,
): Either<L, List<*>> =
    eithers
        .toList()
        .traverse { it }

/**
 * Valid Catch When Null
 *
 * @param L
 * @param R
 * @param appErr
 * @param block
 *
 * @receiver
 *
 * @return
 */
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
                block()
                it.valid()
            },
        ).toValidatedNel()

/**
 * Valid Catch When True
 *
 * @param L
 * @param R
 * @param appErr
 * @param block
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> R.validCatchWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): ValidatedNel<L, R> =
    (if (block(this)) appErr.invalid() else valid()).toValidatedNel()

/**
 * Valid Catch When Apply
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.validCatchWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: R.() -> T,
): ValidatedNel<L, R> =
    try {
        block()
        valid()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.invalid()
    }.toValidatedNel()

/**
 * Valid Catch When Also
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.validCatchWhenAlso(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): ValidatedNel<L, R> =
    try {
        block(this)
        valid()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.invalid()
    }.toValidatedNel()

/**
 * Valid Catch When Run
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.validCatchWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: R.() -> T,
): ValidatedNel<L, T> =
    try {
        block().valid()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.invalid()
    }.toValidatedNel()

/**
 * Valid Catch When Let
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.validCatchWhenLet(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): ValidatedNel<L, T> =
    try {
        block(this).valid()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.invalid()
    }.toValidatedNel()

typealias EitherNel<A, B> = Either<Nel<A>, B>

/**
 * Valid Next When Null
 *
 * @param L
 * @param R
 * @param appErr
 * @param block
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> EitherNel<L, R?>.validNextWhenNull(
    appErr: L,
    block: suspend () -> Unit = {},
): EitherNel<L, R> =
    flatMap {
        it.validCatchWhenNull(appErr, block).toEither()
    }

/**
 * Valid Next When True
 *
 * @param L
 * @param R
 * @param appErr
 * @param block
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> EitherNel<L, R>.validNextWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): EitherNel<L, R> =
    flatMap {
        it.validCatchWhenTrue(appErr, block).toEither()
    }

/**
 * Valid Next When Apply
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: R.() -> T,
): EitherNel<L, R> =
    flatMap {
        it.validCatchWhenApply(appErr, printTrace, block).toEither()
    }

/**
 * Valid Next When Also
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextWhenAlso(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): EitherNel<L, R> =
    flatMap {
        it.validCatchWhenAlso(appErr, printTrace, block).toEither()
    }

/**
 * Valid Next When Run
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: R.() -> T,
): EitherNel<L, T> =
    flatMap {
        it.validCatchWhenRun(appErr, printTrace, block).toEither()
    }

/**
 * Valid Next When Let
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param block
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextWhenLet(
    appErr: L,
    printTrace: Boolean = false,
    block: (R) -> T,
): EitherNel<L, T> =
    flatMap {
        it.validCatchWhenLet(appErr, printTrace, block).toEither()
    }

/**
 * Zip All Valids
 *
 * @param L
 * @param validateNels
 *
 * @return
 */
fun <L : AppErr> zipAllValids(
    vararg validateNels: ValidatedNel<L, *>,
): EitherNel<L, List<*>> =
    validateNels
        .toList()
        .traverse { it }
        .toEither()
