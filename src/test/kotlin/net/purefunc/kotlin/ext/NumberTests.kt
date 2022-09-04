package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NumberTests {

    @Test
    internal fun `test random`() {
        Assertions.assertTrue(random.nextInt(10) > -1)
    }

    @Test
    internal fun `test snowflake sequence`() {
        val snowflakeSeq = SnowflakeSeq(128)

        Assertions.assertTrue(snowflakeSeq.next() > 0)
    }
}
