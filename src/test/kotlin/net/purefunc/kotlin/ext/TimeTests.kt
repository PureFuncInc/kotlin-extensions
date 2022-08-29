package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.concurrent.TimeUnit

class TimeTests {

    @Test
    internal fun `test generate unix milli`() {
        Assertions.assertTrue(unixTime.toString().length == 10)
        Assertions.assertTrue(unixTimeMilli.toString().length == 13)
        Assertions.assertTrue(unixTimeNano.toString().length == 19)
    }

    @Test
    internal fun `test time shift`() {
        val now = unixTimeMilli

        Assertions.assertThrows(RuntimeException::class.java) { now.shift(10, TimeUnit.MICROSECONDS) }

        now.shift(10, TimeUnit.DAYS)
        now.shift(10, TimeUnit.HOURS)
        now.shift(10, TimeUnit.MINUTES)
        now.shift(10, TimeUnit.SECONDS)
        val plus10Millis = now.shift(10, TimeUnit.MILLISECONDS)

        Assertions.assertEquals(now - 10, plus10Millis - 20)
    }
}
