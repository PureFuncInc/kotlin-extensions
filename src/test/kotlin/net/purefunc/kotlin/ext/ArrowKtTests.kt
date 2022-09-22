package net.purefunc.kotlin.ext

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
            val eitherLeftNull: Either<AppErr, String> = map["key2"].eitherCatchWhenNull(NullErr)
            val eitherLeftTrue: Either<AppErr, String?> = map["key1"].eitherCatchWhenTrue(AssertErr) { it!!.length > 3 }
            val eitherLeftApply: Either<AppErr, List<String>> = list.eitherCatchWhenApply(OutBoundErr) { this[100] }
            val eitherLeftAlso: Either<AppErr, List<String>> = list.eitherCatchWhenAlso(OutBoundErr) { it[100] }
            val eitherLeftRun: Either<AppErr, Map<*, *>> = list.eitherCatchWhenRun(CastClassErr) { this as Map<*, *> }
            val eitherLeftLet: Either<AppErr, Map<*, *>> = list.eitherCatchWhenLet(CastClassErr) { it as Map<*, *> }
            val eitherRightNull: Either<AppErr, String> = map["key1"].eitherCatchWhenNull(NullErr)
            val eitherRightTrue: Either<AppErr, String?> = map["key1"].eitherCatchWhenTrue(AssertErr) { it!!.length < 3 }
            val eitherRightApply: Either<AppErr, List<String>> = list.eitherCatchWhenApply(OutBoundErr) { this[0] }
            val eitherRightAlso: Either<AppErr, List<String>> = list.eitherCatchWhenAlso(OutBoundErr) { it[0] }
            val eitherRightRun: Either<AppErr, MutableList<String>> = list.eitherCatchWhenRun(CastClassErr) { this as MutableList<String> }
            val eitherRightLet: Either<AppErr, MutableList<String>> = list.eitherCatchWhenLet(CastClassErr) { it as MutableList<String> }

            Assertions.assertEquals("E500001", eitherLeftNull.assertEitherLeft().code)
            Assertions.assertEquals("Null", eitherLeftNull.assertEitherLeft().message)
            Assertions.assertEquals("E500002", eitherLeftTrue.assertEitherLeft().code)
            Assertions.assertEquals("Assert", eitherLeftTrue.assertEitherLeft().message)
            Assertions.assertEquals("E500003", eitherLeftApply.assertEitherLeft().code)
            Assertions.assertEquals("Out Bound", eitherLeftApply.assertEitherLeft().message)
            Assertions.assertEquals("E500003", eitherLeftAlso.assertEitherLeft().code)
            Assertions.assertEquals("Out Bound", eitherLeftAlso.assertEitherLeft().message)
            Assertions.assertEquals("E500004", eitherLeftRun.assertEitherLeft().code)
            Assertions.assertEquals("Cast Class", eitherLeftRun.assertEitherLeft().message)
            Assertions.assertEquals("E500004", eitherLeftLet.assertEitherLeft().code)
            Assertions.assertEquals("Cast Class", eitherLeftLet.assertEitherLeft().message)

            Assertions.assertEquals("value1", eitherRightNull.assertEitherRight())
            Assertions.assertEquals("value1", eitherRightTrue.assertEitherRight()!!)
            Assertions.assertEquals(list, eitherRightApply.assertEitherRight())
            Assertions.assertEquals(list, eitherRightAlso.assertEitherRight())
            Assertions.assertEquals(list.toMutableList(), eitherRightRun.assertEitherRight())
            Assertions.assertEquals(list.toMutableList(), eitherRightLet.assertEitherRight())

            val eitherAllLeftNull: Either<AppErr, List<*>> =
                zipAllEithers(
                    eitherLeftNull,
                    eitherRightTrue,
                    eitherRightApply,
                    eitherRightAlso,
                    eitherRightRun,
                    eitherRightLet,
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
                    eitherRightLet,
                )
            Assertions.assertEquals("E500002", eitherAllLeftTrue.assertEitherLeft().code)
            Assertions.assertEquals("Assert", eitherAllLeftTrue.assertEitherLeft().message)

            val eitherAllRight: Either<AppErr, List<*>> =
                zipAllEithers(
                    eitherRightNull,
                    eitherRightTrue,
                    eitherRightApply,
                    eitherRightAlso,
                    eitherRightRun,
                    eitherRightLet,
                )
            Assertions.assertEquals(6, eitherAllRight.assertEitherRight().size)
        }

    @Test
    internal fun `test eitherNext series`() =
        runBlocking {
            val eitherLeftNull: Either<AppErr, String> =
                map["key2"]
                    .eitherCatchWhenTrue(AssertErr) { it != null }
                    .eitherNextWhenNull(NullErr) // <- fail here
            Assertions.assertEquals("E500001", eitherLeftNull.assertEitherLeft().code)
            Assertions.assertEquals("Null", eitherLeftNull.assertEitherLeft().message)

            val eitherLeftTrue: Either<AppErr, String> =
                map["key1"]
                    .eitherCatchWhenNull(NullErr)
                    .eitherNextWhenTrue(AssertErr) { it.length > 3 } // <- fail here
            Assertions.assertEquals("E500002", eitherLeftTrue.assertEitherLeft().code)
            Assertions.assertEquals("Assert", eitherLeftTrue.assertEitherLeft().message)

            val eitherLeftApply: Either<AppErr, String> = map["key1"]
                .eitherCatchWhenNull(NullErr)
                .eitherNextWhenTrue(AssertErr) { it.length < 3 }
                .eitherNextWhenApply(OutBoundErr) { listOf(this)[1] } // <- fail here
            Assertions.assertEquals("E500003", eitherLeftApply.assertEitherLeft().code)
            Assertions.assertEquals("Out Bound", eitherLeftApply.assertEitherLeft().message)

            val eitherLeftAlso: Either<AppErr, String> = map["key1"]
                .eitherCatchWhenNull(NullErr)
                .eitherNextWhenTrue(AssertErr) { it.length < 3 }
                .eitherNextWhenApply(OutBoundErr) { listOf(this)[0] }
                .eitherNextWhenAlso(OutBoundErr) { listOf(it)[1] } // <- fail here
            Assertions.assertEquals("E500003", eitherLeftAlso.assertEitherLeft().code)
            Assertions.assertEquals("Out Bound", eitherLeftAlso.assertEitherLeft().message)

            val eitherLeftRun: Either<AppErr, Map<*, *>> =
                map["key1"]
                    .eitherCatchWhenNull(NullErr)
                    .eitherNextWhenTrue(AssertErr) { it.length < 3 }
                    .eitherNextWhenApply(OutBoundErr) { listOf(this)[0] }
                    .eitherNextWhenAlso(OutBoundErr) { listOf(it)[0] }
                    .eitherNextWhenRun(CastClassErr) { listOf(this) as Map<*, *> } // <- fail here
            Assertions.assertEquals("E500004", eitherLeftRun.assertEitherLeft().code)
            Assertions.assertEquals("Cast Class", eitherLeftRun.assertEitherLeft().message)

            val eitherLeftLet: Either<AppErr, Map<*, *>> =
                map["key1"]
                    .eitherCatchWhenNull(NullErr)
                    .eitherNextWhenTrue(AssertErr) { it.length < 3 }
                    .eitherNextWhenApply(OutBoundErr) { listOf(this)[0] }
                    .eitherNextWhenAlso(OutBoundErr) { listOf(it)[0] }
                    .eitherNextWhenRun(CastClassErr) { listOf(this) }
                    .eitherNextWhenLet(CastClassErr) { it as Map<*, *> } // <- fail here
            Assertions.assertEquals("E500004", eitherLeftLet.assertEitherLeft().code)
            Assertions.assertEquals("Cast Class", eitherLeftLet.assertEitherLeft().message)

            val eitherRight: Either<AppErr, List<*>> =
                map["key1"]
                    .eitherCatchWhenNull(NullErr)
                    .eitherNextWhenTrue(AssertErr) { it.length < 3 }
                    .eitherNextWhenApply(OutBoundErr) { listOf(this)[0] }
                    .eitherNextWhenAlso(OutBoundErr) { listOf(it)[0] }
                    .eitherNextWhenRun(CastClassErr) { listOf(this) }
                    .eitherNextWhenLet(CastClassErr) { it as MutableList<*> }
            Assertions.assertEquals(list.toMutableList(), eitherRight.assertEitherRight())
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
    internal fun `test validCatch series`() =
        runBlocking {
            val validatedLeftNull: ValidatedNel<NullErr, String> = map["key2"].validCatchWhenNull(NullErr)
            val validatedLeftTrue: ValidatedNel<AssertErr, String?> = map["key1"].validCatchWhenTrue(AssertErr) { it!!.length > 3 }
            val validatedLeftApply: ValidatedNel<OutBoundErr, List<String>> = list.validCatchWhenApply(OutBoundErr) { this[100] }
            val validatedLeftAlso: ValidatedNel<OutBoundErr, List<String>> = list.validCatchWhenAlso(OutBoundErr) { it[100] }
            val validatedLeftRun: ValidatedNel<CastClassErr, Map<*, *>> = list.validCatchWhenRun(CastClassErr) { this as Map<*, *> }
            val validatedLeftLet: ValidatedNel<CastClassErr, Map<*, *>> = list.validCatchWhenLet(CastClassErr) { it as Map<*, *> }
            val validatedRightNull: ValidatedNel<NullErr, String> = map["key1"].validCatchWhenNull(NullErr)
            val validatedRightTrue: ValidatedNel<AssertErr, String?> = map["key1"].validCatchWhenTrue(AssertErr) { it!!.length < 3 }
            val validatedRightApply: ValidatedNel<OutBoundErr, List<String>> = list.validCatchWhenApply(OutBoundErr) { this[0] }
            val validatedRightAlso: ValidatedNel<OutBoundErr, List<String>> = list.validCatchWhenAlso(OutBoundErr) { it[0] }
            val validatedRightRun: ValidatedNel<CastClassErr, MutableList<String>> = list.validCatchWhenRun(CastClassErr) { this as MutableList<String> }
            val validatedRightLet: ValidatedNel<CastClassErr, MutableList<String>> = list.validCatchWhenLet(CastClassErr) { it as MutableList<String> }

            Assertions.assertEquals("E500001", validatedLeftNull.assertValidateNelLeft()[0].code)
            Assertions.assertEquals("Null", validatedLeftNull.assertValidateNelLeft()[0].message)
            Assertions.assertEquals("E500002", validatedLeftTrue.assertValidateNelLeft()[0].code)
            Assertions.assertEquals("Assert", validatedLeftTrue.assertValidateNelLeft()[0].message)
            Assertions.assertEquals("E500003", validatedLeftApply.assertValidateNelLeft()[0].code)
            Assertions.assertEquals("Out Bound", validatedLeftApply.assertValidateNelLeft()[0].message)
            Assertions.assertEquals("E500003", validatedLeftAlso.assertValidateNelLeft()[0].code)
            Assertions.assertEquals("Out Bound", validatedLeftAlso.assertValidateNelLeft()[0].message)
            Assertions.assertEquals("E500004", validatedLeftRun.assertValidateNelLeft()[0].code)
            Assertions.assertEquals("Cast Class", validatedLeftRun.assertValidateNelLeft()[0].message)
            Assertions.assertEquals("E500004", validatedLeftLet.assertValidateNelLeft()[0].code)
            Assertions.assertEquals("Cast Class", validatedLeftLet.assertValidateNelLeft()[0].message)

            Assertions.assertEquals("value1", validatedRightNull.assertValidateNelRight())
            Assertions.assertEquals("value1", validatedRightTrue.assertValidateNelRight())
            Assertions.assertEquals(list, validatedRightApply.assertValidateNelRight())
            Assertions.assertEquals(list, validatedRightAlso.assertValidateNelRight())
            Assertions.assertEquals(list.toMutableList(), validatedRightRun.assertValidateNelRight())
            Assertions.assertEquals(list.toMutableList(), validatedRightLet.assertValidateNelRight())

            val zipAllValidLeft: EitherNel<AppErr, List<*>> =
                zipAllValids(
                    validatedLeftNull,
                    validatedLeftTrue,
                    validatedLeftApply,
                    validatedLeftAlso,
                    validatedLeftRun,
                    validatedLeftLet,
                )
            Assertions.assertEquals(6, zipAllValidLeft.assertEitherLeft().size)

            val zipAllValidRight: EitherNel<AppErr, List<*>> =
                zipAllValids(
                    validatedRightNull,
                    validatedRightTrue,
                    validatedRightApply,
                    validatedRightAlso,
                    validatedRightRun,
                    validatedRightLet,
                )
            Assertions.assertIterableEquals(
                listOf("value1", "value1", list, list, list, list),
                zipAllValidRight.assertEitherRight()
            )
        }

    @Test
    internal fun `test validNext series`() =
        runBlocking {
            //            val eitherValidLeftTrue: EitherNel<AppErr, String> =
//                zipAllValids(
//                    validatedRightNull,
//                    validatedRightTrue,
//                    validatedRightApply,
//                    validatedRightRun,
//                )
//                    .validNextWhenNull(NullErr)
//                    .validNextWhenTrue(AssertErr) { it.size > 2 } // <- fail here
//                    .validNextWhenApply(OutBoundErr) { it[2] }
//                    .validNextWhenRun(CastClassErr) { map[it[0]] }
//                    .validNextWhenNull(NullErr)
//            Assertions.assertEquals(1, eitherValidLeftTrue.assertEitherLeft().size)
//            Assertions.assertEquals("E500002", eitherValidLeftTrue.assertEitherLeft()[0].code)
//            Assertions.assertEquals("Assert", eitherValidLeftTrue.assertEitherLeft()[0].message)
//
//            val eitherValidLeftApply: EitherNel<AppErr, String> =
//                zipAllValids(
//                    validatedRightNull,
//                    validatedRightTrue,
//                    validatedRightApply,
//                    validatedRightRun,
//                )
//                    .validNextWhenNull(NullErr)
//                    .validNextWhenTrue(AssertErr) { it.size < 2 }
//                    .validNextWhenApply(OutBoundErr) { it[100] } // <- fail here
//                    .validNextWhenRun(CastClassErr) { map[it[0]] }
//                    .validNextWhenNull(NullErr)
//            Assertions.assertEquals(1, eitherValidLeftApply.assertEitherLeft().size)
//            Assertions.assertEquals("E500003", eitherValidLeftApply.assertEitherLeft()[0].code)
//            Assertions.assertEquals("Out Bound", eitherValidLeftApply.assertEitherLeft()[0].message)
//
//            val eitherValidLeftRun: Either<NonEmptyList<AppErr>, Map<*, *>> =
//                zipAllValids(
//                    validatedRightNull,
//                    validatedRightTrue,
//                    validatedRightApply,
//                    validatedRightRun,
//                )
//                    .validNextWhenNull(NullErr)
//                    .validNextWhenTrue(AssertErr) { it.size < 2 }
//                    .validNextWhenApply(OutBoundErr) { it[2] }
//                    .validNextWhenRun(CastClassErr) { it as Map<*, *> } // <- fail here
//                    .validNextWhenNull(NullErr)
//            Assertions.assertEquals(1, eitherValidLeftRun.assertEitherLeft().size)
//            Assertions.assertEquals("E500004", eitherValidLeftRun.assertEitherLeft()[0].code)
//            Assertions.assertEquals("Cast Class", eitherValidLeftRun.assertEitherLeft()[0].message)
//
//            val eitherValidLeftNull: EitherNel<AppErr, String> =
//                zipAllValids(
//                    validatedRightNull,
//                    validatedRightTrue,
//                    validatedRightApply,
//                    validatedRightRun,
//                )
//                    .validNextWhenNull(NullErr)
//                    .validNextWhenTrue(AssertErr) { it.size < 2 }
//                    .validNextWhenApply(OutBoundErr) { it[2] }
//                    .validNextWhenRun(CastClassErr) { map[it[0]] }
//                    .validNextWhenNull(NullErr) // <- fail here
//            Assertions.assertEquals(1, eitherValidLeftNull.assertEitherLeft().size)
//            Assertions.assertEquals("E500001", eitherValidLeftNull.assertEitherLeft()[0].code)
//            Assertions.assertEquals("Null", eitherValidLeftNull.assertEitherLeft()[0].message)
        }
}
