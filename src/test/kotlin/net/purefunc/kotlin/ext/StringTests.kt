package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StringTests {

    @Test
    internal fun `test random alphabetic`() {
        val randomAlphabetic = randomAlphabetic(10)

        Assertions.assertTrue(randomAlphabetic.isNotBlank())
        Assertions.assertTrue(randomAlphabetic.length == 10)
    }

    @Test
    internal fun `test random alphanumeric`() {
        val randomAlphanumeric = randomAlphanumeric(10)

        Assertions.assertTrue(randomAlphanumeric.isNotBlank())
        Assertions.assertTrue(randomAlphanumeric.length == 10)
    }

    @Test
    internal fun `test string url encode and decode`() {
        val rawUrl = "https://purefunc.net/Hello World.jpg"

        val result = rawUrl
            .urlEncode()
            .urlDecode()

        Assertions.assertEquals(rawUrl, result)
    }
}
