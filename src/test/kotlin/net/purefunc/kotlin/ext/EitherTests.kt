package net.purefunc.kotlin.ext

import arrow.core.Either.Companion.catch
import arrow.core.Tuple4
import arrow.core.Tuple5
import arrow.core.Tuple6
import arrow.core.Tuple7
import arrow.core.Tuple8
import arrow.core.Tuple9
import arrow.core.zip
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class EitherTests {

    @Test
    internal fun `test assert`() {
        val list = listOf(1, 2, 3)

        Assertions.assertTrue(catch { list[3] }.isEitherLeft() is ArrayIndexOutOfBoundsException)
        Assertions.assertThrows(AssertionError::class.java) { catch { list.size }.isEitherLeft() }
        Assertions.assertThrows(AssertionError::class.java) { catch { list[3] }.isEitherRight() }
        Assertions.assertEquals(3, catch { list.size }.isEitherRight())
    }

    /**
     * val map = mapOf("key1" to "value1")
     *
     * // VALUE1
     * catch { map["key1"]!! }
     *     .map { it.uppercase(Locale.getDefault()) }
     *     .fold( { println(it) }, { println(it) } )
     *
     * // NullPointerException
     * catch { map["key2"]!! }
     *     .map { it.uppercase(Locale.getDefault()) }
     *     .fold( { println(it) }, { println(it) } )
     *
     * // 10
     * catch { map["key1"]!! }
     *     .flatMap { catch { it.length + "4".toInt() } }
     *     .fold( { println(it) }, { println(it) } )
     *
     * // NumberFormatException
     * catch { map["key1"]!! }
     *     .flatMap { catch { it.length + "a".toInt() } }
     *     .fold( { println(it) }, { println(it) } )
     *
     * // value1
     * catch { map["key1"]!! }
     *     .flatMap { catch { it.length + "4".toInt() } }
     *     .flatMap { catch { map.toList()[it - 10].second } }
     *     .fold( { println(it) }, { println(it) } )
     *
     * // NullPointerException
     * catch { map["key2"]!! }
     *     .flatMap { catch { it.length + "4".toInt() } }
     *     .flatMap { catch { map.toList()[it] } }
     *     .fold( { println(it) }, { println(it) }
     *
     * // NumberFormatException
     * catch { map["key1"]!! }
     *     .flatMap { catch { it.length + "a".toInt() } }
     *     .flatMap { catch { map.toList()[it] } }
     *     .fold( { println(it) }, { println(it) }
     *
     * // IndexOutOfBoundsException
     * catch { map["key1"]!! }
     *     .flatMap { catch { it.length + "4".toInt() } }
     *     .flatMap { catch { map.toList()[it] } }
     *     .fold( { println(it) }, { println(it) }
     */
    @Test
    internal fun `test catchErr series left`() = runBlocking {
        val map = mapOf("key1" to "value1")

        val nullLeft = map["key2"].catchErrWhenNull(RuntimeException("null")).isEitherLeft()
        Assertions.assertTrue(nullLeft is RuntimeException)
        Assertions.assertEquals("null", nullLeft.message)

        val trueLeft = map["key1"].catchErrWhenTrue(RuntimeException("true")) { it == "value1" }.isEitherLeft()
        Assertions.assertTrue(trueLeft is RuntimeException)
        Assertions.assertEquals("true", trueLeft.message)

        val outLeft = map["key1"].catchErrWhenApply(RuntimeException("out")) { listOf(it)[1] }.isEitherLeft()
        Assertions.assertTrue(outLeft is RuntimeException)
        Assertions.assertEquals("out", outLeft.message)

        val illegalLeft =
            map["key1"]!!.catchErrWhenRun(RuntimeException("illegal")) { it.length == "a".toInt() }.isEitherLeft()
        Assertions.assertTrue(illegalLeft is RuntimeException)
        Assertions.assertEquals("illegal", illegalLeft.message)
    }

    @Test
    internal fun `test catchErr series right`() = runBlocking {
        val map = mapOf("key1" to "value1")

        Assertions.assertEquals("value1", map["key1"].catchErrWhenNull(RuntimeException("null")).isEitherRight())

        Assertions.assertEquals(
            "value1",
            map["key1"].catchErrWhenTrue(RuntimeException("true")) { it == "value2" }.isEitherRight()
        )

        Assertions.assertEquals(
            "value1",
            map["key1"].catchErrWhenApply(RuntimeException("out")) { listOf(it)[0] }.isEitherRight()
        )

        Assertions.assertEquals(
            true,
            map["key1"]!!.catchErrWhenRun(RuntimeException("illegal")) { it.length == "6".toInt() }.isEitherRight()
        )
    }

    @Test
    internal fun `test flattenCatchErr series`() = runBlocking {
        val map = mapOf("key1" to "value1")

        val nullLeft = map["key2"].catchErrWhenTrue(RuntimeException("true")) { it != null }
            .flatCatchErrWhenNull(RuntimeException("null"))
//            .flatMap { it: String? -> it.catchErrWhenNull(RuntimeException("null")) }
            .flatCatchErrWhenTrue(RuntimeException("true")) { it == "value1" }
//            .flatMap { outer: String -> outer.catchErrWhenTrue(RuntimeException("true")) { inner: String -> inner == "value1" } }
            .flatCatchErrWhenApply(RuntimeException("out")) { listOf(it)[1] }
//            .flatMap { outer -> outer.catchErrWhenApply(RuntimeException("out")) { listOf(it)[1] } }
            .flatCatchErrWhenRun(RuntimeException("illegal")) { it.length == "a".toInt() }
//            .flatMap { outer -> outer.catchErrWhenRun(RuntimeException("illegal")) { it.length == "a".toInt() } }
            .isEitherLeft()
        Assertions.assertTrue(nullLeft is RuntimeException)
        Assertions.assertEquals("null", nullLeft.message)

        val trueLeft = map["key1"].catchErrWhenTrue(RuntimeException("true")) { it == null }
            .flatCatchErrWhenNull(RuntimeException("null"))
//            .flatMap { it: String? -> it.catchErrWhenNull(RuntimeException("null")) }
            .flatCatchErrWhenTrue(RuntimeException("true")) { it == "value1" }
//            .flatMap { outer: String -> outer.catchErrWhenTrue(RuntimeException("true")) { inner: String -> inner == "value1" } }
            .flatCatchErrWhenApply(RuntimeException("out")) { listOf(it)[1] }
//            .flatMap { outer -> outer.catchErrWhenApply(RuntimeException("out")) { listOf(it)[1] } }
            .flatCatchErrWhenRun(RuntimeException("illegal")) { it.length == "a".toInt() }
//            .flatMap { outer -> outer.catchErrWhenRun(RuntimeException("illegal")) { it.length == "a".toInt() } }
            .isEitherLeft()
        Assertions.assertTrue(trueLeft is RuntimeException)
        Assertions.assertEquals("true", trueLeft.message)

        val outLeft = map["key1"].catchErrWhenTrue(RuntimeException("true")) { it == null }
            .flatCatchErrWhenNull(RuntimeException("null"))
//            .flatMap { it: String? -> it.catchErrWhenNull(RuntimeException("null")) }
            .flatCatchErrWhenTrue(RuntimeException("true")) { it == "value2" }
//            .flatMap { outer: String -> outer.catchErrWhenTrue(RuntimeException("true")) { inner: String -> inner == "value2" } }
            .flatCatchErrWhenApply(RuntimeException("out")) { listOf(it)[1] }
//            .flatMap { outer -> outer.catchErrWhenApply(RuntimeException("out")) { listOf(it)[1] } }
            .flatCatchErrWhenRun(RuntimeException("illegal")) { it.length == "a".toInt() }
//            .flatMap { outer -> outer.catchErrWhenRun(RuntimeException("illegal")) { it.length == "a".toInt() } }
            .isEitherLeft()
        Assertions.assertTrue(outLeft is RuntimeException)
        Assertions.assertEquals("out", outLeft.message)

        val illegalLeft = map["key1"].catchErrWhenTrue(RuntimeException("true")) { it == null }
            .flatCatchErrWhenNull(RuntimeException("null"))
//            .flatMap { it: String? -> it.catchErrWhenNull(RuntimeException("null")) }
            .flatCatchErrWhenTrue(RuntimeException("true")) { it == "value2" }
//            .flatMap { outer: String -> outer.catchErrWhenTrue(RuntimeException("true")) { inner: String -> inner == "value2" } }
            .flatCatchErrWhenApply(RuntimeException("out")) { listOf(it)[0] }
//            .flatMap { outer -> outer.catchErrWhenApply(RuntimeException("out")) { listOf(it)[0] } }
            .flatCatchErrWhenRun(RuntimeException("illegal")) { it.length == "a".toInt() }
//            .flatMap { outer -> outer.catchErrWhenRun(RuntimeException("illegal")) { it.length == "a".toInt() } }
            .isEitherLeft()
        Assertions.assertTrue(illegalLeft is RuntimeException)
        Assertions.assertEquals("illegal", illegalLeft.message)

        val right = map["key1"].catchErrWhenTrue(RuntimeException("true")) { it == null }
            .flatCatchErrWhenNull(RuntimeException("null"))
//            .flatMap { it: String? -> it.catchErrWhenNull(RuntimeException("null")) }
            .flatCatchErrWhenTrue(RuntimeException("true")) { it == "value2" }
//            .flatMap { outer: String -> outer.catchErrWhenTrue(RuntimeException("true")) { inner: String -> inner == "value2" } }
            .flatCatchErrWhenApply(RuntimeException("out")) { listOf(it)[0] }
//            .flatMap { outer -> outer.catchErrWhenApply(RuntimeException("out")) { listOf(it)[0] } }
            .flatCatchErrWhenRun(RuntimeException("illegal")) { it.length == "6".toInt() }
//            .flatMap { outer -> outer.catchErrWhenRun(RuntimeException("illegal")) { it.length == "6".toInt() } }
            .isEitherRight()
        Assertions.assertEquals(true, right)
    }

    /**
     * val list = listOf(1, 2, 3)
     *
     * // 3
     * val valid1: ValidatedNel<Throwable, Int> = Validated.Companion.catch {
     *     list[2]
     * }.toValidatedNel()
     *
     * // 3
     * val valid2: ValidatedNel<Throwable, Int> = Validated.Companion.catch {
     *     list.size
     * }.toValidatedNel()
     *
     * // true
     * val valid3: ValidatedNel<Throwable, Boolean> = Validated.Companion.catch {
     *     list.last() > 0
     * }.toValidatedNel()
     *
     * // java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3)
     * val invalid1: ValidatedNel<Throwable, Int> = Validated.Companion.catch {
     *     list[3]
     * }.toValidatedNel()
     *
     * // java.lang.NumberFormatException: For input string: "a")
     * val invalid2: ValidatedNel<Throwable, Int> = Validated.Companion.catch {
     *     list.size + "a".toInt()
     * }.toValidatedNel()
     *
     * // java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3)
     * val invalid3: ValidatedNel<Throwable, Boolean> = Validated.Companion.catch {
     *     list.last() > list[3]
     * }.toValidatedNel()
     *
     * // Either.Right((3, 3, true))
     * val allRight: Either<NonEmptyList<Throwable>, Triple<Int, Int, Boolean>> =
     *     valid1.zip(valid2, valid3) { v1, v2, v3 -> Triple(v1, v2, v3) }.toEither()
     *
     * // Either.Left(NonEmptyList(java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3))
     * val error1: Either<NonEmptyList<Throwable>, Triple<Int, Int, Boolean>> =
     *     invalid1.zip(valid2, valid3) { v1, v2, v3 -> Triple(v1, v2, v3) }.toEither()
     *
     * // Either.Left(NonEmptyList(java.lang.NumberFormatException: For input string: "a"))
     * val error2: Either<NonEmptyList<Throwable>, Triple<Int, Int, Boolean>> =
     *     valid1.zip(invalid2, valid3) { v1, v2, v3 -> Triple(v1, v2, v3) }.toEither()
     *
     * // Either.Left(NonEmptyList(java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3))
     * val error3: Either<NonEmptyList<Throwable>, Triple<Int, Int, Boolean>> =
     *     valid1.zip(valid2, invalid3) { v1, v2, v3 -> Triple(v1, v2, v3) }.toEither()
     *
     * // Either.Left(NonEmptyList(java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3, java.lang.NumberFormatException: For input string: "a"))
     * val error12: Either<NonEmptyList<Throwable>, Triple<Int, Int, Boolean>> =
     *     invalid1.zip(invalid2, valid3) { v1, v2, v3 -> Triple(v1, v2, v3) }.toEither()
     *
     * // Either.Left(NonEmptyList(java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3, java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3))
     * val error13: Either<NonEmptyList<Throwable>, Triple<Int, Int, Boolean>> =
     *     invalid1.zip(valid2, invalid3) { v1, v2, v3 -> Triple(v1, v2, v3) }.toEither()
     *
     * // Either.Left(NonEmptyList(java.lang.NumberFormatException: For input string: "a", java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3))
     * val error23: Either<NonEmptyList<Throwable>, Triple<Int, Int, Boolean>> =
     *     valid1.zip(invalid2, invalid3) { v1, v2, v3 -> Triple(v1, v2, v3) }.toEither()
     *
     * // Either.Left(NonEmptyList(java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3, java.lang.NumberFormatException: For input string: "a", java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3))
     * val error123: Either<NonEmptyList<Throwable>, Triple<Int, Int, Boolean>> =
     *     invalid1.zip(invalid2, invalid3) { v1, v2, v3 -> Triple(v1, v2, v3) }.toEither()
     */
    @Test
    internal fun `test validErr`() = runBlocking {
        val map = mapOf("key1" to "value1")

        val nullLeft = map["key2"].validErrWhenNull(RuntimeException("null"))
        val nullRight = map["key1"].validErrWhenNull(RuntimeException("null"))
        val trueLeft = map["key1"]!!.validErrWhenTrue(RuntimeException("true")) { it == "value1" }
        val trueRight = map["key1"]!!.validErrWhenTrue(RuntimeException("true")) { it == "value2" }
        val outLeft = map["key1"]!!.validErrWhenApply(RuntimeException("out")) { listOf(it)[1] }
        val outRight = map["key1"]!!.validErrWhenApply(RuntimeException("out")) { listOf(it)[0] }
        val illegalLeft = map["key1"]!!.validErrWhenRun(RuntimeException("illegal")) { it.length == "a".toInt() }
        val illegalRight = map["key1"]!!.validErrWhenRun(RuntimeException("illegal")) { it.length == "2".toInt() }

        val zipLeft =
            nullLeft.zip(trueLeft, outLeft, illegalLeft) { v1, v2, v3, v4 -> Tuple4(v1, v2, v3, v4) }.toEither()
        Assertions.assertEquals(4, zipLeft.isEitherLeft().size)
        val zipRight =
            nullRight.zip(trueRight, outRight, illegalRight) { v1, v2, v3, v4 -> Tuple4(v1, v2, v3, v4) }.toEither()
        Assertions.assertEquals(Tuple4("value1", "value1", "value1", false), zipRight.isEitherRight())
    }

    @Test
    internal fun `test flatValidErr`() = runBlocking {
        val map = mapOf("key1" to "value1", "key2" to "value2")

        val eitherLeftAtValidAll = flatValid2(
            map["key3"].validErrWhenNull(RuntimeException("key3 null")),
            map["key4"].validErrWhenNull(RuntimeException("key4 null")),
        )
            .flatValidErrWhenTrue(RuntimeException("value1 or value2 equals")) {
                it.first == "value1" || it.second == "value2"
            }
            .flatValidErrWhenApply(RuntimeException("index 3 out of bound")) {
                listOf(it.first, it.second)[3]
            }
            .flatValidErrWhenRun(RuntimeException("index 4 out of bound")) {
                listOf(it.first, it.second)[4]
            }
            .flatValidErrWhenNull(RuntimeException("key3 null"))
            .isEitherLeft()
        Assertions.assertEquals(2, eitherLeftAtValidAll.size)
        Assertions.assertEquals("key3 null", eitherLeftAtValidAll[0].message)
        Assertions.assertEquals("key4 null", eitherLeftAtValidAll[1].message)

        val eitherRightTrue = flatValid2(
            map["key1"].validErrWhenNull(RuntimeException()),
            map["key2"].validErrWhenNull(RuntimeException()),
        )
            .flatValidErrWhenTrue(RuntimeException("value1 or value2 equals")) {
                it.first == "value1" || it.second == "value2"
            }
            .flatValidErrWhenApply(RuntimeException("index 3 out of bound")) {
                listOf(it.first, it.second)[3]
            }
            .flatValidErrWhenRun(RuntimeException("index 4 out of bound")) {
                listOf(it.first, it.second)[4]
            }
            .flatValidErrWhenNull(RuntimeException("key3 null"))
            .isEitherLeft()
        Assertions.assertEquals(1, eitherRightTrue.size)
        Assertions.assertEquals("value1 or value2 equals", eitherRightTrue[0].message)

        val eitherRightApply = flatValid2(
            map["key1"].validErrWhenNull(RuntimeException()),
            map["key2"].validErrWhenNull(RuntimeException()),
        )
            .flatValidErrWhenTrue(RuntimeException("value1 or value2 equals")) {
                it.first != "value1" || it.second != "value2"
            }
            .flatValidErrWhenApply(RuntimeException("index 3 out of bound")) {
                listOf(it.first, it.second)[3]
            }
            .flatValidErrWhenRun(RuntimeException("index 4 out of bound")) {
                listOf(it.first, it.second)[4]
            }
            .flatValidErrWhenNull(RuntimeException("key3 null"))
            .isEitherLeft()
        Assertions.assertEquals(1, eitherRightApply.size)
        Assertions.assertEquals("index 3 out of bound", eitherRightApply[0].message)

        val eitherRightRun = flatValid2(
            map["key1"].validErrWhenNull(RuntimeException()),
            map["key2"].validErrWhenNull(RuntimeException()),
        )
            .flatValidErrWhenRun(RuntimeException("index 4 out of bound")) {
                listOf(it.first, it.second)[4]
            }
            .flatValidErrWhenNull(RuntimeException("key3 null"))
            .isEitherLeft()
        Assertions.assertEquals(1, eitherRightRun.size)
        Assertions.assertEquals("index 4 out of bound", eitherRightRun[0].message)

        val eitherRightNull = flatValid2(
            map["key1"].validErrWhenNull(RuntimeException()),
            map["key2"].validErrWhenNull(RuntimeException()),
        )
            .flatValidErrWhenTrue(RuntimeException()) {
                it.first != "value1" || it.second != "value2"
            }
            .flatValidErrWhenApply(RuntimeException()) {
                listOf(it.first, it.second)[0]
            }
            .flatValidErrWhenRun(RuntimeException()) {
                map["key3"]
            }
            .flatValidErrWhenNull(RuntimeException("key3 null"))
            .isEitherLeft()
        Assertions.assertEquals(1, eitherRightNull.size)
        Assertions.assertEquals("key3 null", eitherRightNull[0].message)

        val eitherRight = flatValid2(
            map["key1"].validErrWhenNull(RuntimeException()),
            map["key2"].validErrWhenNull(RuntimeException()),
        )
            .flatValidErrWhenTrue(RuntimeException()) {
                it.first != "value1" || it.second != "value2"
            }
            .flatValidErrWhenApply(RuntimeException()) {
                listOf(it.first, it.second)[0]
            }
            .flatValidErrWhenRun(RuntimeException()) {
                listOf(it.first, it.second)[1]
            }
            .flatValidErrWhenNull(RuntimeException())
            .isEitherRight()
        Assertions.assertEquals("value2", eitherRight)
    }

    @Test
    internal fun `test validAll`() = runBlocking {
        val map = mapOf("key1" to "value1")

        val nullLeft = map["key2"].validErrWhenNull(RuntimeException("null"))
        val nullRight = map["key1"].validErrWhenNull(RuntimeException("null"))
        val trueLeft = map["key1"]!!.validErrWhenTrue(RuntimeException("true")) { it == "value1" }
        val trueRight = map["key1"]!!.validErrWhenTrue(RuntimeException("true")) { it == "value2" }
        val outLeft = map["key1"]!!.validErrWhenApply(RuntimeException("out")) { listOf(it)[1] }
        val outRight = map["key1"]!!.validErrWhenApply(RuntimeException("out")) { listOf(it)[0] }
        val illegalLeft = map["key1"]!!.validErrWhenRun(RuntimeException("illegal")) { it.length == "a".toInt() }
        val illegalRight = map["key1"]!!.validErrWhenRun(RuntimeException("illegal")) { it.length == "2".toInt() }

        Assertions.assertEquals(
            2,
            flatValid2(nullLeft, trueLeft)
                .isEitherLeft()
                .size
        )
        Assertions.assertEquals(
            2,
            flatValid3(nullLeft, trueLeft, outRight)
                .isEitherLeft()
                .size
        )
        Assertions.assertEquals(
            2,
            flatValid4(nullLeft, trueLeft, outRight, illegalRight)
                .isEitherLeft()
                .size
        )
        Assertions.assertEquals(
            3,
            flatValid5(nullLeft, trueLeft, outLeft, illegalRight, nullRight)
                .isEitherLeft()
                .size
        )
        Assertions.assertEquals(
            3,
            flatValid6(nullLeft, trueLeft, outLeft, illegalRight, nullRight, trueRight)
                .isEitherLeft()
                .size
        )
        Assertions.assertEquals(
            3,
            flatValid7(nullLeft, trueLeft, outLeft, illegalRight, nullRight, trueRight, outRight)
                .isEitherLeft()
                .size
        )
        Assertions.assertEquals(
            4,
            flatValid8(nullLeft, trueLeft, outLeft, illegalLeft, nullRight, trueRight, outRight, illegalRight)
                .isEitherLeft()
                .size
        )
        Assertions.assertEquals(
            4,
            flatValid9(
                nullLeft,
                trueLeft,
                outLeft,
                illegalLeft,
                nullRight,
                trueRight,
                outRight,
                illegalRight,
                nullRight
            )
                .isEitherLeft()
                .size
        )

        Assertions.assertEquals(
            Tuple2(
                "value1",
                "value1"
            ),
            flatValid2(
                nullRight,
                trueRight
            ).isEitherRight()
        )
        Assertions.assertEquals(
            Tuple3(
                "value1",
                "value1",
                "value1"
            ),
            flatValid3(
                nullRight,
                trueRight,
                outRight
            ).isEitherRight()
        )
        Assertions.assertEquals(
            Tuple4(
                "value1",
                "value1",
                "value1",
                false
            ),
            flatValid4(
                nullRight,
                trueRight,
                outRight,
                illegalRight
            ).isEitherRight()
        )
        Assertions.assertEquals(
            Tuple5(
                "value1",
                "value1",
                "value1",
                false,
                "value1"
            ),
            flatValid5(
                nullRight,
                trueRight,
                outRight,
                illegalRight,
                nullRight
            ).isEitherRight()
        )
        Assertions.assertEquals(
            Tuple6(
                "value1",
                "value1",
                "value1",
                false,
                "value1",
                "value1"
            ),
            flatValid6(
                nullRight,
                trueRight,
                outRight,
                illegalRight,
                nullRight,
                trueRight
            ).isEitherRight()
        )
        Assertions.assertEquals(
            Tuple7(
                "value1",
                "value1",
                "value1",
                false,
                "value1",
                "value1",
                "value1"
            ),
            flatValid7(
                nullRight,
                trueRight,
                outRight,
                illegalRight,
                nullRight,
                trueRight,
                outRight
            ).isEitherRight()
        )
        Assertions.assertEquals(
            Tuple8(
                "value1",
                "value1",
                "value1",
                false,
                "value1",
                "value1",
                "value1",
                false
            ),
            flatValid8(
                nullRight,
                trueRight,
                outRight,
                illegalRight,
                nullRight,
                trueRight,
                outRight,
                illegalRight
            ).isEitherRight()
        )
        Assertions.assertEquals(
            Tuple9(
                "value1",
                "value1",
                "value1",
                false,
                "value1",
                "value1",
                "value1",
                false,
                "value1"
            ),
            flatValid9(
                nullRight,
                trueRight,
                outRight,
                illegalRight,
                nullRight,
                trueRight,
                outRight,
                illegalRight,
                nullRight
            ).isEitherRight()
        )
    }
}