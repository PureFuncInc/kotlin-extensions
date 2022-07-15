package net.purefunc.kotlin.ext

/**
 * ^ start with
 * + 1 .. N
 * $ end with
 */
fun String.isAlphabetic(): Boolean = matches("^[a-zA-Z]+$".toRegex())

/**
 * ^ start with
 * + 1 .. N
 * $ end with
 */
fun String.isAlphanumeric(): Boolean = matches("^[a-zA-Z\\d]+$".toRegex())
