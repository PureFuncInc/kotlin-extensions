package net.purefunc.kotlin.arrow

import arrow.core.Either
import arrow.core.Nel
import arrow.core.ValidatedNel
import kotlinx.coroutines.runBlocking
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

            val eitherAllLeftNull: Either<AppErr, List<*>> =
                zipAllEithers(
                    eitherLeftNull,
                    eitherRightTrue,
                    eitherRightApply,
                    eitherRightRun,
                )
            Assertions.assertEquals("E500001", eitherAllLeftNull.assertEitherLeft().code)
            Assertions.assertEquals("Null", eitherAllLeftNull.assertEitherLeft().message)

            val eitherAllLeftTrue: Either<AppErr, List<*>> =
                zipAllEithers(
                    eitherRightNull,
                    eitherLeftTrue,
                    eitherLeftApply,
                    eitherRightApply,
                    eitherRightRun,
                )
            Assertions.assertEquals("E500002", eitherAllLeftTrue.assertEitherLeft().code)
            Assertions.assertEquals("Assert", eitherAllLeftTrue.assertEitherLeft().message)

            val eitherAllRight: Either<AppErr, List<*>> =
                zipAllEithers(
                    eitherRightNull,
                    eitherRightTrue,
                    eitherRightApply,
                    eitherRightRun,
                )
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
            val validatedLeftNull: ValidatedNel<NullErr, String> = map["key2"].validNull(NullErr)
            val validatedLeftTrue: ValidatedNel<AssertErr, String?> = map["key1"].validTrue(AssertErr) { it!!.length > 3 }
            val validatedLeftApply: ValidatedNel<OutBoundErr, List<String>> = list.validApply(OutBoundErr) { this[100] }
            val validatedLeftRun: ValidatedNel<CastClassErr, Map<*, *>> = list.validRun(CastClassErr) { this as Map<*, *> }
            val validatedRightNull: ValidatedNel<NullErr, String> = map["key1"].validNull(NullErr)
            val validatedRightTrue: ValidatedNel<AssertErr, String?> = map["key1"].validTrue(AssertErr) { it!!.length < 3 }
            val validatedRightApply: ValidatedNel<OutBoundErr, List<String>> = list.validApply(OutBoundErr) { this[0] }
            val validatedRightRun: ValidatedNel<CastClassErr, MutableList<String>> = list.validRun(CastClassErr) { this as MutableList<String> }

            Assertions.assertEquals("E500001", validatedLeftNull.assertValidateNelLeft()[0].code)
            Assertions.assertEquals("Null", validatedLeftNull.assertValidateNelLeft()[0].message)
            Assertions.assertEquals("E500002", validatedLeftTrue.assertValidateNelLeft()[0].code)
            Assertions.assertEquals("Assert", validatedLeftTrue.assertValidateNelLeft()[0].message)
            Assertions.assertEquals("E500003", validatedLeftApply.assertValidateNelLeft()[0].code)
            Assertions.assertEquals("Out Bound", validatedLeftApply.assertValidateNelLeft()[0].message)
            Assertions.assertEquals("E500004", validatedLeftRun.assertValidateNelLeft()[0].code)
            Assertions.assertEquals("Cast Class", validatedLeftRun.assertValidateNelLeft()[0].message)

            Assertions.assertEquals("value1", validatedRightNull.assertValidateNelRight())
            Assertions.assertEquals("value1", validatedRightTrue.assertValidateNelRight())
            Assertions.assertEquals(list, validatedRightApply.assertValidateNelRight())
            Assertions.assertEquals(list.toMutableList(), validatedRightRun.assertValidateNelRight())

            val zipAllValidLeft: EitherNel<AppErr, List<*>> =
                zipAllValids(
                    validatedLeftNull,
                    validatedLeftTrue,
                    validatedLeftApply,
                    validatedRightRun,
                )
            Assertions.assertEquals(3, zipAllValidLeft.assertEitherLeft().size)

            val zipAllValidRight: EitherNel<AppErr, List<*>> =
                zipAllValids(
                    validatedRightNull,
                    validatedRightTrue,
                    validatedRightApply,
                    validatedRightRun,
                )
            Assertions.assertIterableEquals(
                listOf("value1", "value1", list, list),
                zipAllValidRight.assertEitherRight()
            )

            val validLeftRun =
                zipAllValids(
                    validatedRightNull,
                    validatedRightTrue,
                    validatedRightApply,
                    validatedRightRun,
                )
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
