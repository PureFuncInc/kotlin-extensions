package net.purefunc.kotlin.arrow

import arrow.core.Either
import arrow.core.Nel
import arrow.core.Validated
import arrow.core.ValidatedNel
import kotlinx.coroutines.runBlocking
import net.purefunc.kotlin.arrow.either.eitherApply
import net.purefunc.kotlin.arrow.either.eitherNextApply
import net.purefunc.kotlin.arrow.either.eitherNextNull
import net.purefunc.kotlin.arrow.either.eitherNextRun
import net.purefunc.kotlin.arrow.either.eitherNextTrue
import net.purefunc.kotlin.arrow.either.eitherNextUnit
import net.purefunc.kotlin.arrow.either.eitherNull
import net.purefunc.kotlin.arrow.either.eitherRun
import net.purefunc.kotlin.arrow.either.eitherTrue
import net.purefunc.kotlin.arrow.either.flatEitherApply
import net.purefunc.kotlin.arrow.either.flatEitherNextApply
import net.purefunc.kotlin.arrow.either.flatEitherNextRun
import net.purefunc.kotlin.arrow.either.flatEitherRun
import net.purefunc.kotlin.arrow.either.parallelRunAll
import net.purefunc.kotlin.arrow.validated.EitherNel
import net.purefunc.kotlin.arrow.validated.parallelRunAll
import net.purefunc.kotlin.arrow.validated.validApply
import net.purefunc.kotlin.arrow.validated.validNextApply
import net.purefunc.kotlin.arrow.validated.validNextNull
import net.purefunc.kotlin.arrow.validated.validNextRun
import net.purefunc.kotlin.arrow.validated.validNextTrue
import net.purefunc.kotlin.arrow.validated.validNull
import net.purefunc.kotlin.arrow.validated.validRun
import net.purefunc.kotlin.arrow.validated.validTrue
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ArrowKtTests {

    private inline fun <reified A, B> Either<A, B>.assertEitherLeft(): A =
        when (this) {
            is Either.Left -> value
            is Either.Right -> throw AssertionError()
        }

    private fun <A, B> Either<A, B>.assertEitherRight(): B =
        when (this) {
            is Either.Left -> throw AssertionError()
            is Either.Right -> value
        }

    private val list = listOf("value1")
    private val map = mapOf("key1" to "value1")

    @Test
    internal fun `test eitherCatch series`() =
        runBlocking {
            val eitherLeftNull: Either<AppErr, String> = map["key2"].eitherNull(NullErr)
            val eitherLeftTrue: Either<AppErr, String?> = map["key1"].eitherTrue(AssertErr) { it!!.length > 3 }
            val eitherLeftApply: Either<AppErr, List<String>> = list.eitherApply(OutBoundErr) { this[100] }
            val eitherLeftRun: Either<AppErr, Map<*, *>> = list.eitherRun(CastClassErr) { this as Map<*, *> }
            val eitherRightNull: Either<AppErr, String> = map["key1"].eitherNull(NullErr)
            val eitherRightTrue: Either<AppErr, String?> = map["key1"].eitherTrue(AssertErr) { it!!.length < 3 }
            val eitherRightApply: Either<AppErr, List<String>> = list.eitherApply(OutBoundErr) { this[0] }
            val eitherRightRun: Either<AppErr, MutableList<String>> = list.eitherRun(CastClassErr) { this as MutableList<String> }

            Assertions.assertEquals("E500001", eitherLeftNull.assertEitherLeft().code)
            Assertions.assertEquals("Null", eitherLeftNull.assertEitherLeft().message)
            Assertions.assertEquals("E500002", eitherLeftTrue.assertEitherLeft().code)
            Assertions.assertEquals("Assert", eitherLeftTrue.assertEitherLeft().message)
            Assertions.assertEquals("E500003", eitherLeftApply.assertEitherLeft().code)
            Assertions.assertEquals("Out Bound", eitherLeftApply.assertEitherLeft().message)
            Assertions.assertEquals("E500004", eitherLeftRun.assertEitherLeft().code)
            Assertions.assertEquals("Cast Class", eitherLeftRun.assertEitherLeft().message)

            Assertions.assertEquals("value1", eitherRightNull.assertEitherRight())
            Assertions.assertEquals("value1", eitherRightTrue.assertEitherRight()!!)
            Assertions.assertEquals(list, eitherRightApply.assertEitherRight())
            Assertions.assertEquals(list.toMutableList(), eitherRightRun.assertEitherRight())

            val eitherAllLeftNullList: List<suspend () -> Either<AppErr, *>> = listOf(
                { eitherLeftNull },
                { eitherRightTrue },
                { eitherRightApply },
                { eitherRightRun },
            )
            val eitherAllLeftNull = eitherAllLeftNullList.parallelRunAll()
            Assertions.assertEquals("E500001", eitherAllLeftNull.assertEitherLeft().code)
            Assertions.assertEquals("Null", eitherAllLeftNull.assertEitherLeft().message)

            val eitherAllLeftTrueList: List<suspend () -> Either<AppErr, *>> = listOf(
                { eitherRightNull },
                { eitherLeftTrue },
                { eitherLeftApply },
                { eitherRightApply },
                { eitherRightRun },
            )
            val eitherAllLeftTrue = eitherAllLeftTrueList.parallelRunAll()
            Assertions.assertEquals("E500002", eitherAllLeftTrue.assertEitherLeft().code)
            Assertions.assertEquals("Assert", eitherAllLeftTrue.assertEitherLeft().message)

            val eitherAllRightList: List<suspend () -> Either<AppErr, *>> = listOf(
                { eitherRightNull },
                { eitherRightTrue },
                { eitherRightApply },
                { eitherRightRun },
            )
            val eitherAllRight = eitherAllRightList.parallelRunAll()
            Assertions.assertEquals(4, eitherAllRight.assertEitherRight().size)
        }

    @Test
    internal fun `test eitherNext series`() =
        runBlocking {
            val eitherLeftNull: Either<AppErr, String> =
                map["key2"]
                    .eitherTrue(AssertErr) { it != null }
                    .eitherNextNull(NullErr) // <- fail here
            Assertions.assertEquals("E500001", eitherLeftNull.assertEitherLeft().code)
            Assertions.assertEquals("Null", eitherLeftNull.assertEitherLeft().message)

            val eitherLeftTrue: Either<AppErr, String> =
                map["key1"]
                    .eitherNull(NullErr)
                    .eitherNextTrue(AssertErr) { it.length > 3 } // <- fail here
            Assertions.assertEquals("E500002", eitherLeftTrue.assertEitherLeft().code)
            Assertions.assertEquals("Assert", eitherLeftTrue.assertEitherLeft().message)

            val eitherLeftApply: Either<AppErr, String> = map["key1"]
                .eitherNull(NullErr)
                .eitherNextTrue(AssertErr) { it.length < 3 }
                .eitherNextApply(OutBoundErr) { listOf(this)[1] } // <- fail here
            Assertions.assertEquals("E500003", eitherLeftApply.assertEitherLeft().code)
            Assertions.assertEquals("Out Bound", eitherLeftApply.assertEitherLeft().message)

            val eitherLeftRun: Either<AppErr, Map<*, *>> =
                map["key1"]
                    .eitherNull(NullErr)
                    .eitherNextTrue(AssertErr) { it.length < 3 }
                    .eitherNextApply(OutBoundErr) { listOf(this)[0] }
                    .eitherNextRun(CastClassErr) { listOf(this) as Map<*, *> } // <- fail here
            Assertions.assertEquals("E500004", eitherLeftRun.assertEitherLeft().code)
            Assertions.assertEquals("Cast Class", eitherLeftRun.assertEitherLeft().message)

            val eitherRight: Either<AppErr, List<*>> =
                map["key1"]
                    .eitherNull(NullErr)
                    .eitherNextTrue(AssertErr) { it.length < 3 }
                    .eitherNextApply(OutBoundErr) { listOf(this)[0] }
                    .eitherNextRun(CastClassErr) { listOf(this) }
            Assertions.assertEquals(list.toMutableList(), eitherRight.assertEitherRight())

            val eitherRightUnit: Either<AppErr, Unit> =
                map["key1"]
                    .eitherNull(NullErr)
                    .eitherNextTrue(AssertErr) { it.length < 3 }
                    .eitherNextApply(OutBoundErr) { listOf(this)[0] }
                    .eitherNextRun(CastClassErr) { listOf(this) }
                    .eitherNextUnit()
            eitherRightUnit.assertEitherRight()
        }

    @Test
    internal fun `test flatEitherNext series`() =
        runBlocking {
            val flatEitherApply: Either<AppErr, Map<String, String>> =
                map.flatEitherApply {
                    this["key1"].eitherTrue(AssertErr) { it!!.length > 2 }
                }.flatEitherNextApply {
                    this["key2"].eitherNull(NullErr)
                }
            flatEitherApply.assertEitherRight()

            val flatEitherRun: Either<AppErr, String> =
                map.flatEitherRun {
                    this["key1"].eitherTrue(AssertErr) { it!!.length > 2 }
                }.flatEitherNextRun {
                    map[this].eitherNull(NullErr)
                }
            Assertions.assertEquals("E500002", flatEitherRun.assertEitherLeft().code)
            Assertions.assertEquals("Assert", flatEitherRun.assertEitherLeft().message)

            val flatEitherNextRun: Either<AppErr, String> =
                map.flatEitherRun {
                    this["key1"].eitherTrue(AssertErr) { it!!.length < 2 }
                }.flatEitherNextRun {
                    map[this].eitherNull(NullErr)
                }
            Assertions.assertEquals("E500001", flatEitherNextRun.assertEitherLeft().code)
            Assertions.assertEquals("Null", flatEitherNextRun.assertEitherLeft().message)
        }

    private inline fun <reified A, B> ValidatedNel<A, B>.assertValidateNelLeft(): Nel<A> =
        when (this.toEither()) {
            is Either.Left -> this.toEither().assertEitherLeft()
            is Either.Right -> throw AssertionError()
        }

    private fun <A, B> ValidatedNel<A, B>.assertValidateNelRight(): B =
        when (this.toEither()) {
            is Either.Left -> throw AssertionError()
            is Either.Right -> this.toEither().assertEitherRight()
        }

    @Test
    internal fun `test validCatch and validNext series`() =
        runBlocking {
            val validatedLeftNull: Validated<NullErr, String> = map["key2"].validNull(NullErr)
            val validatedLeftTrue: Validated<AssertErr, String?> = map["key1"].validTrue(AssertErr) { it!!.length > 3 }
            val validatedLeftApply: Validated<OutBoundErr, List<String>> = list.validApply(OutBoundErr) { this[100] }
            val validatedLeftRun: Validated<CastClassErr, Map<*, *>> = list.validRun(CastClassErr) { this as Map<*, *> }
            val validatedRightNull: Validated<NullErr, String> = map["key1"].validNull(NullErr)
            val validatedRightTrue: Validated<AssertErr, String?> = map["key1"].validTrue(AssertErr) { it!!.length < 3 }
            val validatedRightApply: Validated<OutBoundErr, List<String>> = list.validApply(OutBoundErr) { this[0] }
            val validatedRightRun: Validated<CastClassErr, MutableList<String>> = list.validRun(CastClassErr) { this as MutableList<String> }

            val zipAllValidLeftList: List<suspend () -> Validated<AppErr, *>> = listOf(
                { validatedLeftNull },
                { validatedLeftTrue },
                { validatedLeftApply },
                { validatedRightRun },
            )
            val zipAllValidLeft: EitherNel<AppErr, List<*>> = zipAllValidLeftList.parallelRunAll()
            Assertions.assertEquals(3, zipAllValidLeft.assertEitherLeft().size)

            val zipAllValidRightList: List<suspend () -> Validated<AppErr, *>> = listOf(
                { validatedRightNull },
                { validatedRightTrue },
                { validatedRightApply },
                { validatedRightRun },
            )
            val zipAllValidRight: EitherNel<AppErr, List<*>> = zipAllValidRightList.parallelRunAll()
            Assertions.assertIterableEquals(
                listOf("value1", "value1", list, list),
                zipAllValidRight.assertEitherRight()
            )

            val validLeftRun: EitherNel<AppErr, Map<*, *>> = zipAllValidRight
                .validNextNull(NullErr)
                .validNextTrue(AssertErr) {
                    it.isEmpty()
                }.validNextApply(OutBoundErr) {
                    this[0]
                }.validNextRun(CastClassErr) {
                    this as Map<*, *>
                }

            Assertions.assertEquals(1, validLeftRun.assertEitherLeft().size)
            Assertions.assertEquals("E500004", validLeftRun.assertEitherLeft()[0].code)
            Assertions.assertEquals("Cast Class", validLeftRun.assertEitherLeft()[0].message)
        }
}
