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
 * flat represent λ return Either<L, R>
 *
 *     eitherCatchWhenApply -> receiver R           , λ R.() -> T           , return Either<L, R>
 *      eitherNextWhenApply -> receiver Either<L, R>, λ R.() -> T           , return Either<L, R>
 * flatEitherCatchWhenApply -> receiver R           , λ R.() -> Either<L, T>, return Either<L, R>
 *  flatEitherNextWhenApply -> receiver Either<L, R>, λ R.() -> Either<L, T>, return Either<L, R>
 *
 *     eitherCatchWhenRun   -> receiver R           , λ R.() -> T           , return Either<L, T>
 *      eitherNextWhenRun   -> receiver Either<L, R>, λ R.() -> T           , return Either<L, T>
 * flatEitherCatchWhenRun   -> receiver R           , λ R.() -> Either<L, T>, return Either<L, T>
 *  flatEitherNextWhenRun   -> receiver Either<L, R>, λ R.() -> Either<L, T>, return Either<L, T>
 *
 *
 *   validCatchErrWhenApply -> receiver R           , λ R.() -> T           , return ValidatedNel<L, R>
 *    validNextErrWhenApply -> receiver Either<L, R>, λ R.() -> T           , return ValidatedNel<L, R>
 *
 *   validCatchErrWhenRun   -> receiver R           , λ R.() -> T           , return ValidatedNel<L, T>
 *    validNextErrWhenRun   -> receiver Either<L, R>, λ R.() -> T           , return ValidatedNel<L, T>
 */

/**
 * Either Catch When Null
 *
 * @param L
 * @param R
 * @param appErr
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> R?.eitherCatchWhenNull(
    appErr: L,
    λ: suspend () -> Unit = {},
): Either<L, R> =
    toOption()
        .fold(
            ifEmpty = {
                appErr.left()
            },
            ifSome = {
                λ()
                it.right()
            },
        )

/**
 * Either Catch When True
 *
 * @param L
 * @param R
 * @param appErr
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> R.eitherCatchWhenTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): Either<L, R> =
    if (λ(this)) appErr.left()
    else right()

/**
 * Either Catch When Apply
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.eitherCatchWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: R.() -> T,
): Either<L, R> =
    try {
        λ()
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
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.eitherCatchWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: R.() -> T,
): Either<L, T> =
    try {
        λ().right()
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
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> Either<L, R?>.eitherNextWhenNull(
    appErr: L,
    λ: suspend () -> Unit = {},
): Either<L, R> =
    flatMap {
        it.eitherCatchWhenNull(appErr, λ)
    }

/**
 * Either Next When True
 *
 * @param L
 * @param R
 * @param appErr
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> Either<L, R>.eitherNextWhenTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): Either<L, R> =
    flatMap {
        it.eitherCatchWhenTrue(appErr, λ)
    }

/**
 * Either Next When Apply
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: R.() -> T,
): Either<L, R> =
    flatMap {
        it.eitherCatchWhenApply(appErr, printTrace, λ)
    }

/**
 * Either Next When Run
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: R.() -> T,
): Either<L, T> =
    flatMap {
        it.eitherCatchWhenRun(appErr, printTrace, λ)
    }

/**
 * Return Unit
 *
 * @param L
 * @param R
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R> Either<L, R>.eitherNextUnit(
    λ: () -> Either<L, Unit> = { Unit.right() },
): Either<L, Unit> = λ()

/**
 * Flat Either Catch When Apply
 *
 * @param L
 * @param R
 * @param T
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.flatEitherCatchWhenApply(
    λ: R.() -> T,
): Either<L, R> = run {
    λ()
    right()
}

/**
 * Flat Either Catch When Run
 *
 * @param L
 * @param R
 * @param T
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.flatEitherCatchWhenRun(
    λ: R.() -> Either<L, T>,
): Either<L, T> = λ()

/**
 * Flat Either Next When Apply
 *
 * @param L
 * @param R
 * @param T
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextWhenApply(
    λ: R.() -> Either<L, T>,
): Either<L, R> =
    flatMap {
        it.flatEitherCatchWhenApply(λ)
    }

/**
 * Flat Either Next When Run
 *
 * @param L
 * @param R
 * @param T
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextWhenRun(
    λ: R.() -> Either<L, T>,
): Either<L, T> =
    flatMap {
        it.flatEitherCatchWhenRun(λ)
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
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> R?.validCatchWhenNull(
    appErr: L,
    λ: suspend () -> Unit = {},
): ValidatedNel<L, R> =
    toOption()
        .fold(
            ifEmpty = {
                appErr.invalid()
            },
            ifSome = {
                λ()
                it.valid()
            },
        ).toValidatedNel()

/**
 * Valid Catch When True
 *
 * @param L
 * @param R
 * @param appErr
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> R.validCatchWhenTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): ValidatedNel<L, R> =
    (if (λ(this)) appErr.invalid() else valid()).toValidatedNel()

/**
 * Valid Catch When Apply
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.validCatchWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: R.() -> T,
): ValidatedNel<L, R> =
    try {
        λ()
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
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> R.validCatchWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: R.() -> T,
): ValidatedNel<L, T> =
    try {
        λ().valid()
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
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> EitherNel<L, R?>.validNextWhenNull(
    appErr: L,
    λ: suspend () -> Unit = {},
): EitherNel<L, R> =
    flatMap {
        it.validCatchWhenNull(appErr, λ).toEither()
    }

/**
 * Valid Next When True
 *
 * @param L
 * @param R
 * @param appErr
 * @param λ
 *
 * @receiver
 *
 * @return
 */
suspend fun <L : AppErr, R> EitherNel<L, R>.validNextWhenTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): EitherNel<L, R> =
    flatMap {
        it.validCatchWhenTrue(appErr, λ).toEither()
    }

/**
 * Valid Next When Apply
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: R.() -> T,
): EitherNel<L, R> =
    flatMap {
        it.validCatchWhenApply(appErr, printTrace, λ).toEither()
    }

/**
 * Valid Next When Run
 *
 * @param L
 * @param R
 * @param T
 * @param appErr
 * @param printTrace
 * @param λ
 *
 * @receiver
 *
 * @return
 */
inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: R.() -> T,
): EitherNel<L, T> =
    flatMap {
        it.validCatchWhenRun(appErr, printTrace, λ).toEither()
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
