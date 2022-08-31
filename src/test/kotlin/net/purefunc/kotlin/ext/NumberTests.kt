package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NumberTests {

    @Test
    internal fun `test random`() {
        Assertions.assertTrue(random.nextInt(10) > -1)
    }
}
