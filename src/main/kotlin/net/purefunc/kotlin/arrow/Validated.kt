package net.purefunc.kotlin.arrow

import arrow.core.ValidatedNel
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
