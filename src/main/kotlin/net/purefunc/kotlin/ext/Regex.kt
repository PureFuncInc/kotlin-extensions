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

/**
 * ^ start with
 * + 1 .. N
 * ? 0 .. 1
 * * 0 .. N
 * $ end with
 */
fun String.isValidEmail(): Boolean = matches("^[a-zA-Z\\d]+([_.-]?[a-zA-Z\\d]+)*@$".toRegex())
