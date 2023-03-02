package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.security.SecureRandom

class ListTests {

    @Test
    internal fun `test list`() {
        val secureRandom = SecureRandom.getInstanceStrong()

        val words = listOf("pray", "step", "daylight")

        Assertions.assertTrue(words.contains(words randomGetWith secureRandom))
        Assertions.assertThrows(NoSuchElementException::class.java) { listOf<String>() randomGetWith secureRandom }
    }
}
