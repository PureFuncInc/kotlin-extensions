package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class HashTests {

    @Test
    internal fun `test hash`() {
        val rawString = "Hello World"

        Assertions.assertTrue(rawString.md5().isNotEmpty())
        Assertions.assertTrue(rawString.sha3(256).isNotEmpty())
        Assertions.assertTrue(rawString.sha3().isNotEmpty())
    }
}