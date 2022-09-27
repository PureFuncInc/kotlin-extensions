package net.purefunc.kotlin.arrow

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import arrow.core.traverse
import net.purefunc.kotlin.ext.Slf4j.Companion.log

open class AppErr(
    val code: String,
    val message: String,
)

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
