package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class AesTests {

    @Test
    internal fun `test aes encrypt & decrypt`() {
        val rawString = "Hello World"

        val result = rawString
            .aesEncrypt(key = "1234567890123456", iv = "0123456789123456")
            .aesDecrypt(key = "1234567890123456", iv = "0123456789123456")

        Assertions.assertEquals(rawString, result)
    }
}
