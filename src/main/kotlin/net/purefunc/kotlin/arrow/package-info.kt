package net.purefunc.kotlin.arrow

/*
 * next represent extended Either<L, R>
 * flat represent  return Either<L, R>
 *
 *                      receiver       λ                      return
 *         eitherApply  R            , R.() -> T            , Either<L, R>
 *     eitherNextApply  Either<L, R> , R.() -> T            , Either<L, R>
 *     flatEitherApply  R            , R.() -> Either<L, T> , Either<L, R>
 * flatEitherNextApply  Either<L, R> , R.() -> Either<L, T> , Either<L, R>
 *
 *           eitherRun  R            , R.() -> T            , Either<L, T>
 *       eitherNextRun  Either<L, R> , R.() -> T            , Either<L, T>
 *       flatEitherRun  R            , R.() -> Either<L, T> , Either<L, T>
 *   flatEitherNextRun  Either<L, R> , R.() -> Either<L, T> , Either<L, T>
 **
 *          validApply  R            , R.() -> T            , ValidatedNel<L, R>
 *      validNextApply  Either<L, R> , R.() -> T            , ValidatedNel<L, R>
 *
 *            validRun  R            , R.() -> T            , ValidatedNel<L, T>
 *        validNextRun  Either<L, R> , R.() -> T            , ValidatedNel<L, T>
 */
