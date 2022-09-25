package net.purefunc.kotlin.ext

import java.math.BigDecimal
import java.security.SecureRandom
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

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

fun Long.isNotPositive(): Boolean = isZero() || isNegative()
fun Int.isNotPositive(): Boolean = isZero() || isNegative()
fun Float.isNotPositive(): Boolean = isZero() || isNegative()
fun Double.isNotPositive(): Boolean = isZero() || isNegative()
fun BigDecimal.isNotPositive(): Boolean = isZero() || isNegative()
fun Long.isNotNegative(): Boolean = isZero() || isPositive()
fun Int.isNotNegative(): Boolean = isZero() || isPositive()
fun Float.isNotNegative(): Boolean = isZero() || isPositive()
fun Double.isNotNegative(): Boolean = isZero() || isPositive()
fun BigDecimal.isNotNegative(): Boolean = isZero() || isPositive()

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

/**
 * Snowflake Sequence
 *
 * A B                                         C          D
 * 0 00000000000000000000000000000000000000000 0000000000 000000000000
 *
 * A  1 bit, ignore
 * B 41 bit, relative timestamp, (now - basis)
 * C 10 bit, machine index     , max 2^10 = 1024
 * D 12 bit, generate sequence , max 2^12 = 4096
 */
class SnowflakeSeq(
    private val machineIdx: Long,
) {

    // 2021/10/26 00:00:00,000
    private val START_TIMESTAMP = 1635177600000L
    private val MACHINE_BIT = 10
    private val SEQUENCE_BIT = 12

    // 10 bit, 2 ^ 10 = 1024
    private val MAX_MACHINE_INDEX = 1 shl MACHINE_BIT

    // 12 bit, 2 ^ 12 = 4096
    private val MAX_SEQUENCE_INDEX = 1L shl SEQUENCE_BIT

    private var sequence = 0L
    private var lastTimestamp = -1L

    private val milliLock = ReentrantLock()

    init {
        require(machineIdx < MAX_MACHINE_INDEX) { "Machine Index Must Between 0 ~ $MAX_MACHINE_INDEX" }
    }

    fun next(): Long {
        val now = unixTimeMilli

        // check clock backwards
        require(now >= lastTimestamp) { "Clock Moved Backwards. Refusing to Generate Id ..." }

        // even multi thread get the same milli before
        // but only one thread can invoke the block and update lastTimestamp finally
        val currentTimestamp: Long =
            milliLock.withLock {
                // after lock released, the same milli thread will enter the if block
                if (now == lastTimestamp) {
                    if (++sequence == MAX_SEQUENCE_INDEX) {
                        var milli = unixTimeMilli
                        while (milli <= lastTimestamp) {
                            milli = unixTimeMilli
                        }
                        lastTimestamp = milli
                        milli
                    } else {
                        lastTimestamp = now
                        now
                    }
                } else { // not in same millis, reset sequence and update lastTimestamp
                    sequence = 0
                    lastTimestamp = now
                    now
                }
            }

        val timestampBit = ((currentTimestamp - START_TIMESTAMP) shl (MACHINE_BIT + SEQUENCE_BIT))
        val machineBit = (machineIdx shl SEQUENCE_BIT)
        val sequenceBit = sequence

        return timestampBit or machineBit or sequenceBit
    }
}
