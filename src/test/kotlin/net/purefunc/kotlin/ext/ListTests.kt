package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ListTests {

    @Test
    internal fun `test list`() {
        println(random)

        val words = listOf("pray", "step", "daylight")

        Assertions.assertTrue(words.contains(words.randomGet()))
        Assertions.assertThrows(NoSuchElementException::class.java) { listOf<String>().randomGet() }
    }
}
