package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RegexTests {

    @Test
    internal fun `test isAlphabetic`() {
        Assertions.assertFalse("".isAlphabetic())
        Assertions.assertFalse("!".isAlphabetic())
        Assertions.assertFalse("a#".isAlphabetic())

        Assertions.assertTrue("a".isAlphabetic())
        Assertions.assertTrue("B".isAlphabetic())
        Assertions.assertTrue("aB".isAlphabetic())
    }

    @Test
    internal fun `test isAlphanumeric`() {
        Assertions.assertFalse("".isAlphanumeric())
        Assertions.assertFalse("!".isAlphanumeric())
        Assertions.assertFalse("a#".isAlphanumeric())

        Assertions.assertTrue("a".isAlphanumeric())
        Assertions.assertTrue("B".isAlphanumeric())
        Assertions.assertTrue("3".isAlphanumeric())
        Assertions.assertTrue("aB".isAlphanumeric())
        Assertions.assertTrue("B3".isAlphanumeric())
        Assertions.assertTrue("a3".isAlphanumeric())
        Assertions.assertTrue("aB3".isAlphanumeric())
    }

    @Test
    internal fun `test isValidEmail`() {
        Assertions.assertFalse("_vincent@".isValidEmail())
        Assertions.assertTrue("vincent@".isValidEmail())
    }
}