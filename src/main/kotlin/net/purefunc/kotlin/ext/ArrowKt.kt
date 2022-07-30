package net.purefunc.kotlin.ext

import arrow.core.Either
import arrow.core.Nel
import arrow.core.ValidatedNel
import arrow.core.flatMap
import arrow.core.invalid
import arrow.core.left
import arrow.core.right
import arrow.core.toOption
import arrow.core.traverseValidated
import arrow.core.valid
import net.purefunc.kotlin.ext.Slf4j.Companion.log

open class AppErr(
    val code: String,
    val message: String,
)

fun <L : AppErr, R> R?.catchErrWhenNull(
    appErr: L
): Either<L, R> =
    toOption()
        .fold(
            ifEmpty = { appErr.left() },
            ifSome = { it.right() },
        )

suspend fun <L : AppErr, R> R.catchErrWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): Either<L, R> =
    if (block.invoke(this)) appErr.left()
    else right()

suspend inline fun <L : AppErr, reified R, T> R.catchErrWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: suspend (R) -> T,
): Either<L, R> =
    try {
        block.invoke(this)
        right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

suspend inline fun <L : AppErr, reified R, T> R.catchErrWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: suspend (R) -> T,
): Either<L, T> =
    try {
        block.invoke(this).right()
    } catch (tw: Throwable) {
        if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
        appErr.left()
    }

fun <L : AppErr, R> Either<L, R?>.flatCatchErrWhenNull(
    appErr: L,
): Either<L, R> =
    flatMap { r ->
        r.toOption()
            .fold(
                ifEmpty = { appErr.left() },
                ifSome = { it.right() },
            )
    }

suspend fun <L : AppErr, R> Either<L, R>.flatCatchErrWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): Either<L, R> =
    when (this) {
        is Either.Left -> this
        is Either.Right ->
            if (block.invoke(value)) appErr.left()
            else value.right()
    }

suspend fun <L : AppErr, R, T> Either<L, R>.flatCatchErrWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: suspend (R) -> T,
): Either<L, R> =
    when (this) {
        is Either.Left -> this
        is Either.Right ->
            try {
                block.invoke(value)
                value.right()
            } catch (tw: Throwable) {
                if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
                appErr.left()
            }
    }

suspend fun <L : AppErr, R, T> Either<L, R>.flatCatchErrWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: suspend (R) -> T,
): Either<L, T> =
    when (this) {
        is Either.Left -> this
        is Either.Right ->
            try {
                block.invoke(value).right()
            } catch (tw: Throwable) {
                if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
                appErr.left()
            }
    }

fun <L : AppErr, R> R?.validErrWhenNull(
    appErr: L,
): ValidatedNel<L, R> =
    toOption()
        .fold(
            ifEmpty = { appErr.invalid() },
            ifSome = { it.valid() },
        )
        .toValidatedNel()

suspend fun <L : AppErr, R> R.validErrWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): ValidatedNel<L, R> =
    run {
        if (block.invoke(this)) appErr.invalid()
        else valid()
    }.toValidatedNel()

suspend inline fun <L : AppErr, reified R, T> R.validErrWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: suspend (R) -> T,
): ValidatedNel<L, R> =
    run {
        try {
            block.invoke(this)
            valid()
        } catch (tw: Throwable) {
            if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
            appErr.invalid()
        }
    }.toValidatedNel()

suspend inline fun <L : AppErr, reified R, T> R.validErrWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: suspend (R) -> T,
): ValidatedNel<L, T> =
    run {
        try {
            block.invoke(this).valid()
        } catch (tw: Throwable) {
            if (printTrace) log.error(tw.message, tw) else log.error(tw.message)
            appErr.invalid()
        }
    }.toValidatedNel()

typealias EitherNel<A, B> = Either<Nel<A>, B>

fun <L : AppErr, R> EitherNel<L, R?>.flatValidErrWhenNull(
    appErr: L,
): EitherNel<L, R> =
    flatMap { r ->
        r.toOption()
            .fold(
                ifEmpty = { appErr.invalid() },
                ifSome = { it.valid() },
            )
            .toValidatedNel()
            .toEither()
    }

suspend fun <L : AppErr, R> EitherNel<L, R>.flatValidErrWhenTrue(
    appErr: L,
    block: suspend (R) -> Boolean,
): EitherNel<L, R> =
    when (this) {
        is Either.Left -> this
        is Either.Right ->
            run {
                if (block.invoke(value)) appErr.invalid()
                else value.valid()
            }.toValidatedNel().toEither()
    }

suspend fun <L : AppErr, R, T> EitherNel<L, R>.flatValidErrWhenApply(
    appErr: L,
    printTrace: Boolean = false,
    block: suspend (R) -> T,
): EitherNel<L, R> =
    when (this) {
        is Either.Left -> this
        is Either.Right ->
            run {
                try {
                    block.invoke(value)
                    value.valid()
                } catch (tw: Throwable) {
                    log.error(tw.message)
                    appErr.invalid()
                }
            }.toValidatedNel().toEither()
    }

suspend fun <L : AppErr, R, T> EitherNel<L, R>.flatValidErrWhenRun(
    appErr: L,
    printTrace: Boolean = false,
    block: suspend (R) -> T,
): EitherNel<L, T> =
    when (this) {
        is Either.Left -> this
        is Either.Right ->
            run {
                try {
                    block.invoke(value).valid()
                } catch (tw: Throwable) {
                    log.error(tw.message)
                    appErr.invalid()
                }
            }.toValidatedNel().toEither()
    }

fun <L : AppErr> zipAllValid(
    vararg validateNels: ValidatedNel<L, *>
): EitherNel<L, List<*>> =
    validateNels
        .toList()
        .traverseValidated { it }
        .toEither()
