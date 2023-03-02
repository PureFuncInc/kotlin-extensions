package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant

class SnowflakeSeqTests {

    @Test
    internal fun `test snowflake sequence`() {
        val snowflakeSeq = SnowflakeSeq(128)

        Assertions.assertTrue(snowflakeSeq.next() > 0)

        val now = Instant.now().toEpochMilli()
        repeat(4096) {
            snowflakeSeq
                .next(
                    testMode = true,
                    mockNow = now,
                )
        }
    }
}
