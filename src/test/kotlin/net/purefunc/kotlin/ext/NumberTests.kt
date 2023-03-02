package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class NumberTests {

    @Test
    internal fun `test isPositive & isZero & isNegative`() {
        Assertions.assertTrue(2L.isPositive())
        Assertions.assertTrue(2.isPositive())
        Assertions.assertTrue(2F.isPositive())
        Assertions.assertTrue(2.0.isPositive())
        Assertions.assertTrue(BigDecimal("2.0").isPositive())
        Assertions.assertTrue(0L.isNotPositive())
        Assertions.assertTrue(0.isNotPositive())
        Assertions.assertTrue(0F.isNotPositive())
        Assertions.assertTrue(0.0.isNotPositive())
        Assertions.assertTrue(BigDecimal("0.0").isNotPositive())

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
        Assertions.assertTrue(0L.isNotNegative())
        Assertions.assertTrue(0.isNotNegative())
        Assertions.assertTrue(0F.isNotNegative())
        Assertions.assertTrue(0.0.isNotNegative())
        Assertions.assertTrue(BigDecimal("0.0").isNotNegative())

        Assertions.assertTrue(1L.isOdd())
        Assertions.assertTrue(2L.isEven())
        Assertions.assertTrue(1.isOdd())
        Assertions.assertTrue(2.isEven())
    }
}
