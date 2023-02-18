package net.purefunc.kotlin.ext

import java.math.BigDecimal

fun Long.isPositive(): Boolean = this > 0
fun Int.isPositive(): Boolean = this > 0
fun Float.isPositive(): Boolean = this > 0
fun Double.isPositive(): Boolean = this > 0
fun BigDecimal.isPositive(): Boolean = this > BigDecimal.ZERO
fun Long.isNotPositive(): Boolean = isPositive().not()
fun Int.isNotPositive(): Boolean = isPositive().not()
fun Float.isNotPositive(): Boolean = isPositive().not()
fun Double.isNotPositive(): Boolean = isPositive().not()
fun BigDecimal.isNotPositive(): Boolean = isPositive().not()

fun Long.isZero(): Boolean = this == 0L
fun Int.isZero(): Boolean = this == 0
fun Float.isZero(): Boolean = this == 0F
fun Double.isZero(): Boolean = this == 0.0
fun BigDecimal.isZero(): Boolean = this.compareTo(BigDecimal.ZERO) == 0

fun Long.isNegative(): Boolean = this < 0
fun Int.isNegative(): Boolean = this < 0
fun Float.isNegative(): Boolean = this < 0
fun Double.isNegative(): Boolean = this < 0
fun BigDecimal.isNegative(): Boolean = this < BigDecimal.ZERO
fun Long.isNotNegative(): Boolean = isNegative().not()
fun Int.isNotNegative(): Boolean = isNegative().not()
fun Float.isNotNegative(): Boolean = isNegative().not()
fun Double.isNotNegative(): Boolean = isNegative().not()
fun BigDecimal.isNotNegative(): Boolean = isNegative().not()

fun Long.isEven(): Boolean = mod(2L).isZero()
fun Long.isOdd(): Boolean = isEven().not()
fun Int.isEven(): Boolean = mod(2).isZero()
fun Int.isOdd(): Boolean = isEven().not()
