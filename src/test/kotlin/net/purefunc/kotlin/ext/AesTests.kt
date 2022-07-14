package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AesTests {

    @Test
    internal fun `test aes encrypt & decrypt`() {
        val rawString = "Hello World"

        val result = rawString
            .aesEncrypt(aesKey = "1234567890123456", aesIv = "0123456789123456")
            .aesDecrypt(aesKey = "1234567890123456", aesIv = "0123456789123456")

        Assertions.assertEquals(rawString, result)
    }
}