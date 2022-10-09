package net.purefunc.kotlin.arrow

import arrow.core.Either
import arrow.core.Validated
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
import net.purefunc.kotlin.arrow.either.zipAll
import net.purefunc.kotlin.arrow.validated.validApply
import net.purefunc.kotlin.arrow.validated.validNextApply
import net.purefunc.kotlin.arrow.validated.validNextNull
import net.purefunc.kotlin.arrow.validated.validNextRun
import net.purefunc.kotlin.arrow.validated.validNextTrue
import net.purefunc.kotlin.arrow.validated.validNull
import net.purefunc.kotlin.arrow.validated.validRun
import net.purefunc.kotlin.arrow.validated.validTrue
import net.purefunc.kotlin.arrow.validated.zipAll
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
            val eitherLeftNull: Either<AppErr, String> = map["key2"].eitherNull(Type1Err)
            val eitherLeftTrue: Either<AppErr, String?> = map["key1"].eitherTrue(Type2Err) { it!!.length > 3 }
            val eitherLeftApply: Either<AppErr, List<String>> = list.eitherApply(Type3Err) { this[100] }
            val eitherLeftRun: Either<AppErr, Map<*, *>> = list.eitherRun(Type4Err) { this as Map<*, *> }
            val eitherRightNull: Either<AppErr, String> = map["key1"].eitherNull(Type1Err)
            val eitherRightTrue: Either<AppErr, String?> = map["key1"].eitherTrue(Type2Err) { it!!.length < 3 }
            val eitherRightApply: Either<AppErr, List<String>> = list.eitherApply(Type3Err) { this[0] }
            val eitherRightRun: Either<AppErr, MutableList<String>> = list.eitherRun(Type4Err) { this as MutableList<String> }

            Assertions.assertEquals("E500001", eitherLeftNull.assertEitherLeft().code)
            Assertions.assertEquals("Type1", eitherLeftNull.assertEitherLeft().message)
            Assertions.assertEquals("E500002", eitherLeftTrue.assertEitherLeft().code)
            Assertions.assertEquals("Type2", eitherLeftTrue.assertEitherLeft().message)
            Assertions.assertEquals("E500003", eitherLeftApply.assertEitherLeft().code)
            Assertions.assertEquals("Type3", eitherLeftApply.assertEitherLeft().message)
            Assertions.assertEquals("E500004", eitherLeftRun.assertEitherLeft().code)
            Assertions.assertEquals("Type4", eitherLeftRun.assertEitherLeft().message)

            Assertions.assertEquals("value1", eitherRightNull.assertEitherRight())
            Assertions.assertEquals("value1", eitherRightTrue.assertEitherRight()!!)
            Assertions.assertEquals(list, eitherRightApply.assertEitherRight())
            Assertions.assertEquals(list.toMutableList(), eitherRightRun.assertEitherRight())

            val eitherAllLeftNull = listOf(
                eitherLeftNull,
                eitherRightTrue,
                eitherRightApply,
                eitherRightRun
            ).zipAll()
            Assertions.assertEquals("E500001", eitherAllLeftNull.assertEitherLeft().code)
            Assertions.assertEquals("Type1", eitherAllLeftNull.assertEitherLeft().message)

            val eitherAllLeftTrue = listOf(
                eitherRightNull,
                eitherLeftTrue,
                eitherLeftApply,
                eitherRightApply,
                eitherRightRun,
            ).zipAll()
            Assertions.assertEquals("E500002", eitherAllLeftTrue.assertEitherLeft().code)
            Assertions.assertEquals("Type2", eitherAllLeftTrue.assertEitherLeft().message)

            val eitherAllRight = listOf(
                eitherRightNull,
                eitherRightTrue,
                eitherRightApply,
                eitherRightRun,
            ).zipAll()
            Assertions.assertEquals(4, eitherAllRight.assertEitherRight().size)
        }

    @Test
    internal fun `test eitherNext series`() =
        runBlocking {
            val eitherLeftNull: Either<AppErr, String> =
                map["key2"]
                    .eitherTrue(Type2Err) { it != null }
                    .eitherNextNull(Type1Err) // <- fail here
            Assertions.assertEquals("E500001", eitherLeftNull.assertEitherLeft().code)
            Assertions.assertEquals("Type1", eitherLeftNull.assertEitherLeft().message)

            val eitherLeftTrue: Either<AppErr, String> =
                map["key1"]
                    .eitherNull(Type1Err)
                    .eitherNextTrue(Type2Err) { it.length > 3 } // <- fail here
            Assertions.assertEquals("E500002", eitherLeftTrue.assertEitherLeft().code)
            Assertions.assertEquals("Type2", eitherLeftTrue.assertEitherLeft().message)

            val eitherLeftApply: Either<AppErr, String> = map["key1"]
                .eitherNull(Type1Err)
                .eitherNextTrue(Type2Err) { it.length < 3 }
                .eitherNextApply(Type3Err) { listOf(this)[1] } // <- fail here
            Assertions.assertEquals("E500003", eitherLeftApply.assertEitherLeft().code)
            Assertions.assertEquals("Type3", eitherLeftApply.assertEitherLeft().message)

            val eitherLeftRun: Either<AppErr, Map<*, *>> =
                map["key1"]
                    .eitherNull(Type1Err)
                    .eitherNextTrue(Type2Err) { it.length < 3 }
                    .eitherNextApply(Type3Err) { listOf(this)[0] }
                    .eitherNextRun(Type4Err) { listOf(this) as Map<*, *> } // <- fail here
            Assertions.assertEquals("E500004", eitherLeftRun.assertEitherLeft().code)
            Assertions.assertEquals("Type4", eitherLeftRun.assertEitherLeft().message)

            val eitherRight: Either<AppErr, List<*>> =
                map["key1"]
                    .eitherNull(Type1Err)
                    .eitherNextTrue(Type2Err) { it.length < 3 }
                    .eitherNextApply(Type3Err) { listOf(this)[0] }
                    .eitherNextRun(Type4Err) { listOf(this) }
            Assertions.assertEquals(list.toMutableList(), eitherRight.assertEitherRight())

            val eitherRightUnit: Either<AppErr, Unit> =
                map["key1"]
                    .eitherNull(Type1Err)
                    .eitherNextTrue(Type2Err) { it.length < 3 }
                    .eitherNextApply(Type3Err) { listOf(this)[0] }
                    .eitherNextRun(Type4Err) { listOf(this) }
                    .eitherNextUnit()
            eitherRightUnit.assertEitherRight()
        }

    @Test
    internal fun `test flatEitherNext series`() =
        runBlocking {
            val flatEitherApply: Either<AppErr, Map<String, String>> =
                map.flatEitherApply {
                    this["key1"].eitherTrue(Type2Err) { it!!.length > 2 }
                }.flatEitherNextApply {
                    this["key2"].eitherNull(Type1Err)
                }
            flatEitherApply.assertEitherRight()

            val flatEitherRun: Either<AppErr, String> =
                map.flatEitherRun {
                    this["key1"].eitherTrue(Type2Err) { it!!.length > 2 }
                }.flatEitherNextRun {
                    map[this].eitherNull(Type1Err)
                }
            Assertions.assertEquals("E500002", flatEitherRun.assertEitherLeft().code)
            Assertions.assertEquals("Type2", flatEitherRun.assertEitherLeft().message)

            val flatEitherNextRun: Either<AppErr, String> =
                map.flatEitherRun {
                    this["key1"].eitherTrue(Type2Err) { it!!.length < 2 }
                }.flatEitherNextRun {
                    map[this].eitherNull(Type1Err)
                }
            Assertions.assertEquals("E500001", flatEitherNextRun.assertEitherLeft().code)
            Assertions.assertEquals("Type1", flatEitherNextRun.assertEitherLeft().message)
        }

    @Test
    internal fun `test validCatch and validNext series`() =
        runBlocking {
            val validatedLeftNull: Validated<Type1Err, String> = map["key2"].validNull(Type1Err)
            val validatedLeftTrue: Validated<Type2Err, String?> = map["key1"].validTrue(Type2Err) { it!!.length > 3 }
            val validatedLeftApply: Validated<Type3Err, List<String>> = list.validApply(Type3Err) { this[100] }
            val validatedLeftRun: Validated<Type4Err, Map<*, *>> = list.validRun(Type4Err) { this as Map<*, *> }
            val validatedRightNull: Validated<Type1Err, String> = map["key1"].validNull(Type1Err)
            val validatedRightTrue: Validated<Type2Err, String?> = map["key1"].validTrue(Type2Err) { it!!.length < 3 }
            val validatedRightApply: Validated<Type3Err, List<String>> = list.validApply(Type3Err) { this[0] }
            val validatedRightRun: Validated<Type4Err, MutableList<String>> = list.validRun(Type4Err) { this as MutableList<String> }

            val zipAllValidLeft = listOf(
                validatedLeftNull,
                validatedLeftTrue,
                validatedLeftApply,
                validatedLeftRun
            ).zipAll()
            Assertions.assertEquals(4, zipAllValidLeft.assertEitherLeft().size)

            val zipAllValidRight = listOf(
                validatedRightNull,
                validatedRightTrue,
                validatedRightApply,
                validatedRightRun,
            ).zipAll()
            Assertions.assertIterableEquals(
                listOf("value1", "value1", list, list),
                zipAllValidRight.assertEitherRight()
            )

            val validLeftRun = zipAllValidRight
                .validNextNull(Type1Err)
                .validNextTrue(Type2Err) {
                    it.isEmpty()
                }.validNextApply(Type3Err) {
                    this[0]
                }.validNextRun(Type4Err) {
                    this as Map<*, *>
                }

            Assertions.assertEquals(1, validLeftRun.assertEitherLeft().size)
            Assertions.assertEquals("E500004", validLeftRun.assertEitherLeft()[0].code)
            Assertions.assertEquals("Type4", validLeftRun.assertEitherLeft()[0].message)
        }
}
