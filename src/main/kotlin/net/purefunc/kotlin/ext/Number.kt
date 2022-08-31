package net.purefunc.kotlin.ext

import java.math.BigDecimal
import java.security.SecureRandom

val random: SecureRandom
    inline get() = SecureRandom.getInstanceStrong()

/**
 * Is Positive
 *
 * @return
 */
fun Long.isPositive(): Boolean = this > 0

/**
 * Is Positive
 *
 * @return
 */
fun Int.isPositive(): Boolean = this > 0

/**
 * Is Positive
 *
 * @return
 */
fun Float.isPositive(): Boolean = this > 0

/**
 * Is Positive
 *
 * @return
 */
fun Double.isPositive(): Boolean = this > 0

/**
 * Is Positive
 *
 * @return
 */
fun BigDecimal.isPositive(): Boolean = this > BigDecimal.ZERO

/**
 * Is Zero
 *
 * @return
 */
fun Long.isZero(): Boolean = this == 0L

/**
 * Is Zero
 *
 * @return
 */
fun Int.isZero(): Boolean = this == 0

/**
 * Is Zero
 *
 * @return
 */
fun Float.isZero(): Boolean = this == 0F

/**
 * Is Zero
 *
 * @return
 */
fun Double.isZero(): Boolean = this == 0.0

/**
 * Is Zero
 *
 * @return
 */
fun BigDecimal.isZero(): Boolean = this.compareTo(BigDecimal.ZERO) == 0

/**
 * Is Negative
 *
 * @return
 */
fun Long.isNegative(): Boolean = this < 0

/**
 * Is Negative
 *
 * @return
 */
fun Int.isNegative(): Boolean = this < 0

/**
 * Is Negative
 *
 * @return
 */
fun Float.isNegative(): Boolean = this < 0

/**
 * Is Negative
 *
 * @return
 */
fun Double.isNegative(): Boolean = this < 0

/**
 * Is Negative
 *
 * @return
 */
fun BigDecimal.isNegative(): Boolean = this < BigDecimal.ZERO

/**
 * Is Even
 *
 * @return
 */
fun Long.isEven(): Boolean = mod(2L).isZero()

/**
 * Is Odd
 *
 * @return
 */
fun Long.isOdd(): Boolean = !isEven()

/**
 * Is Even
 *
 * @return
 */
fun Int.isEven(): Boolean = mod(2).isZero()

/**
 * Is Odd
 *
 * @return
 */
fun Int.isOdd(): Boolean = !isEven()
