package net.purefunc.kotlin.ext

import arrow.core.Either
import arrow.core.NonEmptyList
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

    @Test
    internal fun `test catchErr series`() =
        runBlocking {
            val list = listOf("value1")
            val map = mapOf("key1" to "value1")

            val eitherLeftNull: Either<AppErr, String> = map["key2"].catchErrWhenNull(NullErr)
            Assertions.assertEquals("E500001", eitherLeftNull.assertEitherLeft().code)
            Assertions.assertEquals("Null", eitherLeftNull.assertEitherLeft().message)

            val eitherLeftTrue: Either<AppErr, String?> = map["key1"].catchErrWhenTrue(AssertErr) { it!!.length > 3 }
            Assertions.assertEquals("E500002", eitherLeftTrue.assertEitherLeft().code)
            Assertions.assertEquals("Assert", eitherLeftTrue.assertEitherLeft().message)

            val eitherLeftFalse: Either<AppErr, String?> = map["key1"].catchErrWhenFalse(AssertErr) { it!!.length < 3 }
            Assertions.assertEquals("E500002", eitherLeftFalse.assertEitherLeft().code)
            Assertions.assertEquals("Assert", eitherLeftFalse.assertEitherLeft().message)

            val eitherLeftApply: Either<AppErr, List<String>> = list.catchErrWhenApply(OutBoundErr) { it[100] }
            Assertions.assertEquals("E500003", eitherLeftApply.assertEitherLeft().code)
            Assertions.assertEquals("Out Bound", eitherLeftApply.assertEitherLeft().message)

            val eitherLeftRun: Either<AppErr, Map<*, *>> = list.catchErrWhenRun(CastClassErr) { it as Map<*, *> }
            Assertions.assertEquals("E500004", eitherLeftRun.assertEitherLeft().code)
            Assertions.assertEquals("Cast Class", eitherLeftRun.assertEitherLeft().message)

            val eitherRightNull: Either<AppErr, String> = map["key1"].catchErrWhenNull(NullErr)
            Assertions.assertEquals("value1", eitherRightNull.assertEitherRight())

            val eitherRightTrue: Either<AppErr, String?> = map["key1"].catchErrWhenTrue(AssertErr) { it!!.length < 3 }
            Assertions.assertEquals("value1", eitherRightTrue.assertEitherRight()!!)

            val eitherRightFalse: Either<AppErr, String?> = map["key1"].catchErrWhenFalse(AssertErr) { it!!.length > 3 }
            Assertions.assertEquals("value1", eitherRightFalse.assertEitherRight()!!)

            val eitherRightApply: Either<AppErr, List<String>> = list.catchErrWhenApply(OutBoundErr) { it[0] }
            Assertions.assertEquals(list, eitherRightApply.assertEitherRight())

            val eitherRightRun: Either<AppErr, MutableList<String>> = list.catchErrWhenRun(CastClassErr) { it as MutableList<String> }
            Assertions.assertEquals(list.toMutableList(), eitherRightRun.assertEitherRight())

            val eitherAllRight: Either<AppErr, List<*>> =
                zipAllEither(
                    eitherRightNull,
                    eitherRightTrue,
                    eitherRightFalse,
                    eitherRightApply,
                    eitherRightRun,
                )
            Assertions.assertEquals(5, eitherAllRight.assertEitherRight().size)

            val eitherAllLeftNull: Either<AppErr, List<*>> =
                zipAllEither(
                    eitherLeftNull,
                    eitherRightTrue,
                    eitherRightFalse,
                    eitherRightApply,
                    eitherRightRun,
                )
            Assertions.assertEquals("E500001", eitherAllLeftNull.assertEitherLeft().code)
            Assertions.assertEquals("Null", eitherAllLeftNull.assertEitherLeft().message)

            val eitherAllLeftTrue: Either<AppErr, List<*>> =
                zipAllEither(
                    eitherRightNull,
                    eitherLeftTrue,
                    eitherRightFalse,
                    eitherRightApply,
                    eitherRightRun,
                )
            Assertions.assertEquals("E500002", eitherAllLeftTrue.assertEitherLeft().code)
            Assertions.assertEquals("Assert", eitherAllLeftTrue.assertEitherLeft().message)

            val eitherAllLeftFalse: Either<AppErr, List<*>> =
                zipAllEither(
                    eitherRightNull,
                    eitherRightTrue,
                    eitherLeftFalse,
                    eitherRightApply,
                    eitherRightRun,
                )
            Assertions.assertEquals("E500002", eitherAllLeftFalse.assertEitherLeft().code)
            Assertions.assertEquals("Assert", eitherAllLeftFalse.assertEitherLeft().message)

            val eitherAllLeftApply: Either<AppErr, List<*>> =
                zipAllEither(
                    eitherRightNull,
                    eitherRightTrue,
                    eitherRightFalse,
                    eitherLeftApply,
                    eitherRightRun,
                )
            Assertions.assertEquals("E500003", eitherAllLeftApply.assertEitherLeft().code)
            Assertions.assertEquals("Out Bound", eitherAllLeftApply.assertEitherLeft().message)

            val eitherAllLeftRun: Either<AppErr, List<*>> =
                zipAllEither(
                    eitherRightNull,
                    eitherRightTrue,
                    eitherRightFalse,
                    eitherRightApply,
                    eitherLeftRun,
                )
            Assertions.assertEquals("E500004", eitherAllLeftRun.assertEitherLeft().code)
            Assertions.assertEquals("Cast Class", eitherAllLeftRun.assertEitherLeft().message)
        }

    @Test
    internal fun `test flattenCatchErr series`() =
        runBlocking {
            val list = listOf("value1")
            val map = mapOf("key1" to "value1")

            val eitherLeftNull: Either<AppErr, Map<*, *>> =
                map["key2"]
                    .catchErrWhenTrue(AssertErr) { it != null }
                    .flatCatchErrWhenNull(NullErr) // <- fail here
                    .flatCatchErrWhenTrue(AssertErr) { it.length > 3 }
                    .flatCatchErrWhenFalse(AssertErr) { it.length < 3 }
                    .flatCatchErrWhenApply(OutBoundErr) { listOf(it)[1] }
                    .flatCatchErrWhenRun(CastClassErr) { listOf(it) as Map<*, *> }
            Assertions.assertEquals("E500001", eitherLeftNull.assertEitherLeft().code)
            Assertions.assertEquals("Null", eitherLeftNull.assertEitherLeft().message)

            val eitherLeftTrue: Either<AppErr, Map<*, *>> =
                map["key1"]
                    .catchErrWhenTrue(AssertErr) { it == null }
                    .flatCatchErrWhenNull(NullErr)
                    .flatCatchErrWhenTrue(AssertErr) { it.length > 3 } // <- fail here
                    .flatCatchErrWhenFalse(AssertErr) { it.length < 3 }
                    .flatCatchErrWhenApply(OutBoundErr) { listOf(it)[1] }
                    .flatCatchErrWhenRun(CastClassErr) { listOf(it) as Map<*, *> }
            Assertions.assertEquals("E500002", eitherLeftTrue.assertEitherLeft().code)
            Assertions.assertEquals("Assert", eitherLeftTrue.assertEitherLeft().message)

            val eitherLeftFalse: Either<AppErr, Map<*, *>> =
                map["key1"]
                    .catchErrWhenTrue(AssertErr) { it == null }
                    .flatCatchErrWhenNull(NullErr)
                    .flatCatchErrWhenTrue(AssertErr) { it.length < 3 }
                    .flatCatchErrWhenFalse(AssertErr) { it.length < 3 } // <- fail here
                    .flatCatchErrWhenApply(OutBoundErr) { listOf(it)[1] }
                    .flatCatchErrWhenRun(CastClassErr) { listOf(it) as Map<*, *> }
            Assertions.assertEquals("E500002", eitherLeftFalse.assertEitherLeft().code)
            Assertions.assertEquals("Assert", eitherLeftFalse.assertEitherLeft().message)

            val eitherLeftApply: Either<AppErr, Map<*, *>> = map["key1"]
                .catchErrWhenTrue(AssertErr) { it == null }
                .flatCatchErrWhenNull(NullErr)
                .flatCatchErrWhenTrue(AssertErr) { it.length < 3 }
                .flatCatchErrWhenFalse(AssertErr) { it.length > 3 }
                .flatCatchErrWhenApply(OutBoundErr) { listOf(it)[1] } // <- fail here
                .flatCatchErrWhenRun(CastClassErr) { listOf(it) as Map<*, *> }
            Assertions.assertEquals("E500003", eitherLeftApply.assertEitherLeft().code)
            Assertions.assertEquals("Out Bound", eitherLeftApply.assertEitherLeft().message)

            val eitherLeftRun: Either<AppErr, Map<*, *>> =
                map["key1"]
                    .catchErrWhenTrue(AssertErr) { it == null }
                    .flatCatchErrWhenNull(NullErr)
                    .flatCatchErrWhenTrue(AssertErr) { it.length < 3 }
                    .flatCatchErrWhenFalse(AssertErr) { it.length > 3 }
                    .flatCatchErrWhenApply(OutBoundErr) { listOf(it)[0] }
                    .flatCatchErrWhenRun(CastClassErr) { listOf(it) as Map<*, *> } // <- fail here
            Assertions.assertEquals("E500004", eitherLeftRun.assertEitherLeft().code)
            Assertions.assertEquals("Cast Class", eitherLeftRun.assertEitherLeft().message)

            val eitherRight: Either<AppErr, List<*>> =
                map["key1"]
                    .catchErrWhenTrue(AssertErr) { it == null }
                    .flatCatchErrWhenNull(NullErr)
                    .flatCatchErrWhenTrue(AssertErr) { it.length < 3 }
                    .flatCatchErrWhenFalse(AssertErr) { it.length > 3 }
                    .flatCatchErrWhenApply(OutBoundErr) { listOf(it)[0] }
                    .flatCatchErrWhenRun(CastClassErr) { listOf(it) as MutableList<*> }
            Assertions.assertEquals(list.toMutableList(), eitherRight.assertEitherRight())
        }

    @Test
    internal fun `test validErr and flatValidErr series`() =
        runBlocking {
            val list = listOf("value1")
            val map = mapOf("key1" to "value1")

            val validatedLeftNull: ValidatedNel<NullErr, String> = map["key2"].validErrWhenNull(NullErr)
            val validatedLeftTrue: ValidatedNel<AssertErr, String?> = map["key1"].validErrWhenTrue(AssertErr) { it!!.length > 3 }
            val validatedLeftFalse: ValidatedNel<AssertErr, String?> = map["key1"].validErrWhenFalse(AssertErr) { it!!.length < 3 }
            val validatedLeftApply: ValidatedNel<OutBoundErr, List<String>> = list.validErrWhenApply(OutBoundErr) { it[100] }
            val validatedLeftRun: ValidatedNel<CastClassErr, Map<*, *>> = list.validErrWhenRun(CastClassErr) { it as Map<*, *> }
            val validatedRightNull: ValidatedNel<NullErr, String> = map["key1"].validErrWhenNull(NullErr)
            val validatedRightTrue: ValidatedNel<AssertErr, String?> = map["key1"].validErrWhenTrue(AssertErr) { it!!.length < 3 }
            val validatedRightFalse: ValidatedNel<AssertErr, String?> = map["key1"].validErrWhenFalse(AssertErr) { it!!.length > 3 }
            val validatedRightApply: ValidatedNel<OutBoundErr, List<String>> = list.validErrWhenApply(OutBoundErr) { it[0] }
            val validatedRightRun: ValidatedNel<CastClassErr, MutableList<String>> = list.validErrWhenRun(CastClassErr) { it as MutableList<String> }

            val zipAllValidLeft: EitherNel<AppErr, List<*>> =
                zipAllValid(
                    validatedLeftNull,
                    validatedLeftTrue,
                    validatedLeftFalse,
                    validatedLeftApply,
                    validatedLeftRun,
                )
            Assertions.assertEquals(5, zipAllValidLeft.assertEitherLeft().size)

            val zipAllValidRight: EitherNel<AppErr, List<*>> =
                zipAllValid(
                    validatedRightNull,
                    validatedRightTrue,
                    validatedRightFalse,
                    validatedRightApply,
                    validatedRightRun,
                )
            Assertions.assertIterableEquals(
                listOf("value1", "value1", "value1", list, list),
                zipAllValidRight.assertEitherRight()
            )

            val eitherValidLeftTrue: EitherNel<AppErr, String> =
                zipAllValid(
                    validatedRightNull,
                    validatedRightTrue,
                    validatedRightFalse,
                    validatedRightApply,
                    validatedRightRun,
                )
                    .flatValidErrWhenNull(NullErr)
                    .flatValidErrWhenTrue(AssertErr) { it.size > 2 } // <- fail here
                    .flatValidErrWhenFalse(AssertErr) { it.size > 2 }
                    .flatValidErrWhenApply(OutBoundErr) { it[2] }
                    .flatValidErrWhenRun(CastClassErr) { map[it[0]] }
                    .flatValidErrWhenNull(NullErr)
            Assertions.assertEquals(1, eitherValidLeftTrue.assertEitherLeft().size)
            Assertions.assertEquals("E500002", eitherValidLeftTrue.assertEitherLeft()[0].code)
            Assertions.assertEquals("Assert", eitherValidLeftTrue.assertEitherLeft()[0].message)

            val eitherValidLeftFalse: EitherNel<AppErr, String> =
                zipAllValid(
                    validatedRightNull,
                    validatedRightTrue,
                    validatedRightFalse,
                    validatedRightApply,
                    validatedRightRun,
                )
                    .flatValidErrWhenNull(NullErr)
                    .flatValidErrWhenTrue(AssertErr) { it.size < 2 }
                    .flatValidErrWhenFalse(AssertErr) { it.size < 2 }  // <- fail here
                    .flatValidErrWhenApply(OutBoundErr) { it[2] }
                    .flatValidErrWhenRun(CastClassErr) { map[it[0]] }
                    .flatValidErrWhenNull(NullErr)
            Assertions.assertEquals(1, eitherValidLeftFalse.assertEitherLeft().size)
            Assertions.assertEquals("E500002", eitherValidLeftFalse.assertEitherLeft()[0].code)
            Assertions.assertEquals("Assert", eitherValidLeftFalse.assertEitherLeft()[0].message)

            val eitherValidLeftApply: EitherNel<AppErr, String> =
                zipAllValid(
                    validatedRightNull,
                    validatedRightTrue,
                    validatedRightFalse,
                    validatedRightApply,
                    validatedRightRun,
                )
                    .flatValidErrWhenNull(NullErr)
                    .flatValidErrWhenTrue(AssertErr) { it.size < 2 }
                    .flatValidErrWhenFalse(AssertErr) { it.size > 2 }
                    .flatValidErrWhenApply(OutBoundErr) { it[100] } // <- fail here
                    .flatValidErrWhenRun(CastClassErr) { map[it[0]] }
                    .flatValidErrWhenNull(NullErr)
            Assertions.assertEquals(1, eitherValidLeftApply.assertEitherLeft().size)
            Assertions.assertEquals("E500003", eitherValidLeftApply.assertEitherLeft()[0].code)
            Assertions.assertEquals("Out Bound", eitherValidLeftApply.assertEitherLeft()[0].message)

            val eitherValidLeftRun: Either<NonEmptyList<AppErr>, Map<*, *>> =
                zipAllValid(
                    validatedRightNull,
                    validatedRightFalse,
                    validatedRightTrue,
                    validatedRightApply,
                    validatedRightRun,
                )
                    .flatValidErrWhenNull(NullErr)
                    .flatValidErrWhenTrue(AssertErr) { it.size < 2 }
                    .flatValidErrWhenFalse(AssertErr) { it.size > 2 }
                    .flatValidErrWhenApply(OutBoundErr) { it[2] }
                    .flatValidErrWhenRun(CastClassErr) { it as Map<*, *> } // <- fail here
                    .flatValidErrWhenNull(NullErr)
            Assertions.assertEquals(1, eitherValidLeftRun.assertEitherLeft().size)
            Assertions.assertEquals("E500004", eitherValidLeftRun.assertEitherLeft()[0].code)
            Assertions.assertEquals("Cast Class", eitherValidLeftRun.assertEitherLeft()[0].message)

            val eitherValidLeftNull: EitherNel<AppErr, String> =
                zipAllValid(
                    validatedRightNull,
                    validatedRightFalse,
                    validatedRightTrue,
                    validatedRightApply,
                    validatedRightRun,
                )
                    .flatValidErrWhenNull(NullErr)
                    .flatValidErrWhenTrue(AssertErr) { it.size < 2 }
                    .flatValidErrWhenFalse(AssertErr) { it.size > 2 }
                    .flatValidErrWhenApply(OutBoundErr) { it[2] }
                    .flatValidErrWhenRun(CastClassErr) { map[it[0]] }
                    .flatValidErrWhenNull(NullErr) // <- fail here
            Assertions.assertEquals(1, eitherValidLeftNull.assertEitherLeft().size)
            Assertions.assertEquals("E500001", eitherValidLeftNull.assertEitherLeft()[0].code)
            Assertions.assertEquals("Null", eitherValidLeftNull.assertEitherLeft()[0].message)
        }
}
