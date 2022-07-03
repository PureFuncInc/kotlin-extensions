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
}