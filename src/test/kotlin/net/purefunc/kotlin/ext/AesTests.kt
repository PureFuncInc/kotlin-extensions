package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AesTests {

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
}