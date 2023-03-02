package net.purefunc.kotlin.ext

import java.time.Instant
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

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
    private val machineIdx: Int,
) {

    // 2021/10/26 00:00:00,000
    private val START_TIMESTAMP: Long = 1635177600000L
    private val MACHINE_BIT: Int = 10
    private val SEQUENCE_BIT: Int = 12

    // 10 bit, 2 ^ 10 = 1024
    private val MAX_MACHINE_INDEX: Int = 1 shl MACHINE_BIT

    // 12 bit, 2 ^ 12 = 4096
    private val MAX_SEQUENCE_INDEX: Int = 1 shl SEQUENCE_BIT

    private var sequence: Int = 0
    private var lastTimestamp: Long = -1L

    private val milliLock: ReentrantLock = ReentrantLock()

    init {
        require(machineIdx < MAX_MACHINE_INDEX) { "Machine Index Must Between 0 ~ $MAX_MACHINE_INDEX" }
    }

    fun next(
        testMode: Boolean = false,
        mockNow: Long = 0,
    ): Long {
        // consider Multi-thread request in same millisecond
        // last   , 1676704091000 111
        // thread1, 1676704091000 233
        // thread2, 1676704091000 547
        // thread3, 1676704091000 876
        val now = if (testMode) mockNow else Instant.now().toEpochMilli()

        // check clock backwards
        require(now >= lastTimestamp) { "Clock Moved Backwards. Refusing to Generate Id ..." }

        // even multi thread get the same milli before
        // but only one thread can invoke the block and update $lastTimestamp finally
        val currentTimestamp: Long = milliLock
            .withLock {
                // after lock released, the thread in same millisecond will enter the if block
                if (now == lastTimestamp) {
                    // when $sequence equals 4096, renew lastTimestamp until $lastTimestamp != $now
                    if (++sequence == MAX_SEQUENCE_INDEX) {
                        var updatedNow = if (testMode) mockNow else Instant.now().toEpochMilli()
                        while (updatedNow <= lastTimestamp) {
                            updatedNow = Instant.now().toEpochMilli()
                        }
                        lastTimestamp = updatedNow
                        updatedNow
                    } else { // $sequence < 4096, update $lastTimestamp ($sequence already increase at if block
                        lastTimestamp = now
                        now
                    }
                } else { // not in same millisecond, reset $sequence and update $lastTimestamp
                    sequence = 0
                    lastTimestamp = now
                    now
                }
            }

        val timestampBit = ((currentTimestamp - START_TIMESTAMP) shl (MACHINE_BIT + SEQUENCE_BIT))
        val machineBit = (machineIdx.toLong() shl SEQUENCE_BIT)
        val sequenceBit = sequence.toLong()

        return timestampBit or machineBit or sequenceBit
    }
}
