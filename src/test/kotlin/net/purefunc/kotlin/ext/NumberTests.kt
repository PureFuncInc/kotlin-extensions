package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class NumberTests {

    @Test
    internal fun `test random`() {
        Assertions.assertTrue(random.nextInt(10) > -1)
    }

    @Test
    internal fun `test isPositive & isZero & isNegative`() {
        Assertions.assertTrue(2L.isPositive())
        Assertions.assertTrue(2.isPositive())
        Assertions.assertTrue(2F.isPositive())
        Assertions.assertTrue(2.0.isPositive())
        Assertions.assertTrue(BigDecimal("2.0").isPositive())

        Assertions.assertTrue(0L.isZero())
        Assertions.assertTrue(0.isZero())
        Assertions.assertTrue(0F.isZero())
        Assertions.assertTrue(0.0.isZero())
        Assertions.assertTrue(BigDecimal("0.0").isZero())

        Assertions.assertTrue((-2L).isNegative())
        Assertions.assertTrue((-2).isNegative())
        Assertions.assertTrue((-2F).isNegative())
        Assertions.assertTrue((-2.0).isNegative())
        Assertions.assertTrue(BigDecimal("-2.0").isNegative())

        Assertions.assertTrue(0L.isNotPositive())
        Assertions.assertTrue(0.isNotPositive())
        Assertions.assertTrue(0F.isNotPositive())
        Assertions.assertTrue(0.0.isNotPositive())
        Assertions.assertTrue(BigDecimal("0.0").isNotPositive())

        Assertions.assertTrue(0L.isNotNegative())
        Assertions.assertTrue(0.isNotNegative())
        Assertions.assertTrue(0F.isNotNegative())
        Assertions.assertTrue(0.0.isNotNegative())
        Assertions.assertTrue(BigDecimal("0.0").isNotNegative())
    }

    @Test
    internal fun `test snowflake sequence`() {
        val snowflakeSeq = SnowflakeSeq(128)

        Assertions.assertTrue(snowflakeSeq.next() > 0)
    }
}
