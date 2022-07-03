package net.purefunc.kotlin.ext

import arrow.core.Either.Companion.catch
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

        val exceptionLeft =
            map["key1"]!!.catchErrWhenMap(RuntimeException("illegal")) { it.length == "a".toInt() }
                .isEitherLeft()
        Assertions.assertTrue(exceptionLeft is RuntimeException)
        Assertions.assertEquals("illegal", exceptionLeft.message)
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
            map["key1"]!!.catchErrWhenMap(RuntimeException("illegal")) { it.length == "6".toInt() }.isEitherRight()
        )
    }

    @Test
    internal fun `test flattenCatchErr series`() = runBlocking {
        val map = mapOf("key1" to "value1")

        val nullLeft = map["key2"].catchErrWhenTrue(RuntimeException("true")) { it != null }
            .flattenCatchErrWhenNull(RuntimeException("null"))
//            .flatMap { it: String? -> it.catchErrWhenNull(RuntimeException("null")) }
            .flattenCatchErrWhenTrue(RuntimeException("true")) { it == "value1" }
//            .flatMap { outer: String -> outer.catchErrWhenTrue(RuntimeException("true")) { inner: String -> inner == "value1" } }
            .flattenCatchErrWhenApply(RuntimeException("out")) { listOf(it)[1] }
//            .flatMap { outer -> outer.catchErrWhenApply(RuntimeException("out")) { listOf(it)[1] } }
            .flattenCatchErrWhenMap(RuntimeException("illegal")) { it.length == "a".toInt() }
//            .flatMap { outer -> outer.catchErrWhenException(RuntimeException("illegal")) { it.length == "a".toInt() } }
            .isEitherLeft()
        Assertions.assertTrue(nullLeft is RuntimeException)
        Assertions.assertEquals("null", nullLeft.message)

        val trueLeft = map["key1"].catchErrWhenTrue(RuntimeException("true")) { it == null }
            .flattenCatchErrWhenNull(RuntimeException("null"))
//            .flatMap { it: String? -> it.catchErrWhenNull(RuntimeException("null")) }
            .flattenCatchErrWhenTrue(RuntimeException("true")) { it == "value1" }
//            .flatMap { outer: String -> outer.catchErrWhenTrue(RuntimeException("true")) { inner: String -> inner == "value1" } }
            .flattenCatchErrWhenApply(RuntimeException("out")) { listOf(it)[1] }
//            .flatMap { outer -> outer.catchErrWhenApply(RuntimeException("out")) { listOf(it)[1] } }
            .flattenCatchErrWhenMap(RuntimeException("illegal")) { it.length == "a".toInt() }
//            .flatMap { outer -> outer.catchErrWhenException(RuntimeException("illegal")) { it.length == "a".toInt() } }
            .isEitherLeft()
        Assertions.assertTrue(trueLeft is RuntimeException)
        Assertions.assertEquals("true", trueLeft.message)

        val outLeft = map["key1"].catchErrWhenTrue(RuntimeException("true")) { it == null }
            .flattenCatchErrWhenNull(RuntimeException("null"))
//            .flatMap { it: String? -> it.catchErrWhenNull(RuntimeException("null")) }
            .flattenCatchErrWhenTrue(RuntimeException("true")) { it == "value2" }
//            .flatMap { outer: String -> outer.catchErrWhenTrue(RuntimeException("true")) { inner: String -> inner == "value2" } }
            .flattenCatchErrWhenApply(RuntimeException("out")) { listOf(it)[1] }
//            .flatMap { outer -> outer.catchErrWhenApply(RuntimeException("out")) { listOf(it)[1] } }
            .flattenCatchErrWhenMap(RuntimeException("illegal")) { it.length == "a".toInt() }
//            .flatMap { outer -> outer.catchErrWhenException(RuntimeException("illegal")) { it.length == "a".toInt() } }
            .isEitherLeft()
        Assertions.assertTrue(outLeft is RuntimeException)
        Assertions.assertEquals("out", outLeft.message)

        val illegalLeft = map["key1"].catchErrWhenTrue(RuntimeException("true")) { it == null }
            .flattenCatchErrWhenNull(RuntimeException("null"))
//            .flatMap { it: String? -> it.catchErrWhenNull(RuntimeException("null")) }
            .flattenCatchErrWhenTrue(RuntimeException("true")) { it == "value2" }
//            .flatMap { outer: String -> outer.catchErrWhenTrue(RuntimeException("true")) { inner: String -> inner == "value2" } }
            .flattenCatchErrWhenApply(RuntimeException("out")) { listOf(it)[0] }
//            .flatMap { outer -> outer.catchErrWhenApply(RuntimeException("out")) { listOf(it)[0] } }
            .flattenCatchErrWhenMap(RuntimeException("illegal")) { it.length == "a".toInt() }
//            .flatMap { outer -> outer.catchErrWhenException(RuntimeException("illegal")) { it.length == "a".toInt() } }
            .isEitherLeft()
        Assertions.assertTrue(illegalLeft is RuntimeException)
        Assertions.assertEquals("illegal", illegalLeft.message)

        val right = map["key1"].catchErrWhenTrue(RuntimeException("true")) { it == null }
            .flattenCatchErrWhenNull(RuntimeException("null"))
//            .flatMap { it: String? -> it.catchErrWhenNull(RuntimeException("null")) }
            .flattenCatchErrWhenTrue(RuntimeException("true")) { it == "value2" }
//            .flatMap { outer: String -> outer.catchErrWhenTrue(RuntimeException("true")) { inner: String -> inner == "value2" } }
            .flattenCatchErrWhenApply(RuntimeException("out")) { listOf(it)[0] }
//            .flatMap { outer -> outer.catchErrWhenApply(RuntimeException("out")) { listOf(it)[0] } }
            .flattenCatchErrWhenMap(RuntimeException("illegal")) { it.length == "6".toInt() }
//            .flatMap { outer -> outer.catchErrWhenException(RuntimeException("illegal")) { it.length == "6".toInt() } }
            .isEitherRight()
        Assertions.assertEquals(true, right)
    }
}