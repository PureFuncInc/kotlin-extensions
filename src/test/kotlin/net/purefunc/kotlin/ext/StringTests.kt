package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StringTests {

    @Test
    internal fun `test random UUID`() {
        Assertions.assertTrue(randomUUID.isNotEmpty())
    }

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
    internal fun `test base64 encode & decode`() {
        val rawString = "Hello World"

        val encodeBase64 = rawString.toByteArray().encodeBase64()
        val base64Decode = encodeBase64.base64Decode()

        Assertions.assertEquals(rawString, base64Decode.string())
    }

    @Test
    internal fun `test string url encode and decode`() {
        val rawUrl = "https://purefunc.net/Hello World.jpg"

        Assertions.assertEquals(rawUrl, rawUrl.urlEncode().urlDecode())
    }
}
