package net.purefunc.kotlin.ext

import net.purefunc.kotlin.ext.Slf4j.Companion.log
import org.junit.jupiter.api.Test

class Slf4jTests {

    @Test
    internal fun `test log`() {
        log.info("test log")
    }
}