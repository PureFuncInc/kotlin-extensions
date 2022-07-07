package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StringTests {

    @Test
    internal fun `test random UUID`() {
        Assertions.assertTrue(randomUUID().isNotEmpty())
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

        Assertions.assertEquals(rawString, rawString.toByteArray().encodeBase64().base64Decode())
    }

    @Test
    internal fun `test aes encrypt & decrypt`() {
        val rawString = "Hello World"
        val rawString2 = "Hello World2"

        val transformation = Transformation.AES_CBC_PKCS7PADDING
        val aesKeyPair = AesKeyPair.create("1234567890123456", "0123456789123456")

        val result = "Hello World"
            .aesEncrypt(transformation, aesKeyPair)
            .aesDecrypt(transformation, aesKeyPair)

        Assertions.assertEquals(rawString, result)

        val result2 = "Hello World2"
            .aesEncrypt(aesKeyPair = aesKeyPair)
            .aesDecrypt(aesKeyPair = aesKeyPair)

        Assertions.assertEquals(rawString2, result2)
    }

    @Test
    internal fun `test hash`() {
        val rawString = "Hello World"

        Assertions.assertTrue(rawString.md5().isNotEmpty())
        Assertions.assertTrue(rawString.sha3(256).isNotEmpty())
        Assertions.assertTrue(rawString.sha3().isNotEmpty())
    }
}