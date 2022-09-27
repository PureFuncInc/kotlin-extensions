package net.purefunc.kotlin.arrow

import arrow.core.Either
import arrow.core.Nel
import arrow.core.ValidatedNel
import arrow.core.flatMap
import arrow.core.invalid
import arrow.core.toOption
import arrow.core.traverse
import arrow.core.valid
import net.purefunc.kotlin.ext.Slf4j.Companion.log

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
