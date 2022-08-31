package net.purefunc.kotlin.ext

/**
 * Is Alphabetic
 *
 * ^ start with
 * + 1 .. N
 * $ end with
 *
 * @return
 */
fun String.isAlphabetic(): Boolean = matches("^[a-zA-Z]+$".toRegex())

/**
 * Is Alphanumeric
 *
 * ^ start with
 * + 1 .. N
 * $ end with
 *
 * @return
 */
fun String.isAlphanumeric(): Boolean = matches("^[a-zA-Z\\d]+$".toRegex())
