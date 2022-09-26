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
 *         eitherApply -> receiver R           , λ R.() -> T           , return Either<L, R>
 *     eitherNextApply -> receiver Either<L, R>, λ R.() -> T           , return Either<L, R>
 *     flatEitherApply -> receiver R           , λ R.() -> Either<L, T>, return Either<L, R>
 * flatEitherNextApply -> receiver Either<L, R>, λ R.() -> Either<L, T>, return Either<L, R>
 *
 *           eitherRun -> receiver R           , λ R.() -> T           , return Either<L, T>
 *       eitherNextRun -> receiver Either<L, R>, λ R.() -> T           , return Either<L, T>
 *       flatEitherRun -> receiver R           , λ R.() -> Either<L, T>, return Either<L, T>
 *   flatEitherNextRun -> receiver Either<L, R>, λ R.() -> Either<L, T>, return Either<L, T>
 *
 *
 *          validApply -> receiver R           , λ R.() -> T           , return ValidatedNel<L, R>
 *      validNextApply -> receiver Either<L, R>, λ R.() -> T           , return ValidatedNel<L, R>
 *
 *            validRun -> receiver R           , λ R.() -> T           , return ValidatedNel<L, T>
 *        validNextRun -> receiver Either<L, R>, λ R.() -> T           , return ValidatedNel<L, T>
 */

/**
 * Either Null
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
suspend fun <L : AppErr, R> R?.eitherNull(
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
 * Either True
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
suspend fun <L : AppErr, R> R.eitherTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): Either<L, R> =
    if (λ(this)) appErr.left()
    else right()

/**
 * Either Apply
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
suspend inline fun <L : AppErr, reified R, T> R.eitherApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, R> =
    try {
        λ()
        right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

/**
 * Either Run
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
suspend inline fun <L : AppErr, reified R, T> R.eitherRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, T> =
    try {
        λ().right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

/**
 * Either Null
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
suspend fun <L : AppErr, R> Either<L, R?>.eitherNextNull(
    appErr: L,
    λ: suspend () -> Unit = {},
): Either<L, R> =
    flatMap {
        it.eitherNull(appErr, λ)
    }

/**
 * Either True
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
suspend fun <L : AppErr, R> Either<L, R>.eitherNextTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): Either<L, R> =
    flatMap {
        it.eitherTrue(appErr, λ)
    }

/**
 * Either Apply
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
suspend inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, R> =
    flatMap {
        it.eitherApply(appErr, printTrace, λ)
    }

/**
 * Either Run
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
suspend inline fun <L : AppErr, reified R, T> Either<L, R>.eitherNextRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): Either<L, T> =
    flatMap {
        it.eitherRun(appErr, printTrace, λ)
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
inline fun <L : AppErr, R> Either<L, R>.eitherNextUnit(
    λ: () -> Either<L, Unit> = { Unit.right() },
): Either<L, Unit> = λ()

/**
 * Flat Either Apply
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
suspend inline fun <L : AppErr, reified R, T> R.flatEitherApply(
    λ: suspend R.() -> Either<L, T>,
): Either<L, R> =
    run {
        λ()
        right()
    }

/**
 * Flat Either Run
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
suspend inline fun <L : AppErr, reified R, T> R.flatEitherRun(
    λ: suspend R.() -> Either<L, T>,
): Either<L, T> = λ()

/**
 * Flat Either Apply
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
suspend inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextApply(
    λ: suspend R.() -> Either<L, T>,
): Either<L, R> =
    flatMap {
        it.flatEitherApply(λ)
    }

/**
 * Flat Either Run
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
suspend inline fun <L : AppErr, reified R, T> Either<L, R>.flatEitherNextRun(
    λ: suspend R.() -> Either<L, T>,
): Either<L, T> =
    flatMap {
        it.flatEitherRun(λ)
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
 * Valid Null
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
suspend fun <L : AppErr, R> R?.validNull(
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
 * Valid True
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
suspend fun <L : AppErr, R> R.validTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): ValidatedNel<L, R> =
    (if (λ(this)) appErr.invalid() else valid()).toValidatedNel()

/**
 * Valid Apply
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
suspend inline fun <L : AppErr, reified R, T> R.validApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): ValidatedNel<L, R> =
    try {
        λ()
        valid()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.invalid()
    }.toValidatedNel()

/**
 * Valid Run
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
suspend inline fun <L : AppErr, reified R, T> R.validRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): ValidatedNel<L, T> =
    try {
        λ().valid()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.invalid()
    }.toValidatedNel()

typealias EitherNel<A, B> = Either<Nel<A>, B>

/**
 * Valid Null
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
suspend fun <L : AppErr, R> EitherNel<L, R?>.validNextNull(
    appErr: L,
    λ: suspend () -> Unit = {},
): EitherNel<L, R> =
    flatMap {
        it.validNull(appErr, λ).toEither()
    }

/**
 * Valid True
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
suspend fun <L : AppErr, R> EitherNel<L, R>.validNextTrue(
    appErr: L,
    λ: suspend (R) -> Boolean,
): EitherNel<L, R> =
    flatMap {
        it.validTrue(appErr, λ).toEither()
    }

/**
 * Valid Apply
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
suspend inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextApply(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): EitherNel<L, R> =
    flatMap {
        it.validApply(appErr, printTrace, λ).toEither()
    }

/**
 * Valid Run
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
suspend inline fun <L : AppErr, reified R, T> EitherNel<L, R>.validNextRun(
    appErr: L,
    printTrace: Boolean = false,
    λ: suspend R.() -> T,
): EitherNel<L, T> =
    flatMap {
        it.validRun(appErr, printTrace, λ).toEither()
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
