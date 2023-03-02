package net.purefunc.kotlin.ext

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset
import java.security.SecureRandom

private val secureRandom: SecureRandom = SecureRandom.getInstanceStrong()
private val alphabetic: List<Char> = ('a'..'z') + ('A'..'Z')
private val alphanumerics: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

fun randomAlphabetic(
    length: Int,
    spliterator: String = "",
): String =
    (1..length)
        .map { alphabetic randomGetWith secureRandom }
        .joinToString(spliterator)

fun randomAlphanumeric(
    length: Int,
    spliterator: String = "",
): String =
    (1..length)
        .map { alphanumerics randomGetWith secureRandom }
        .joinToString(spliterator)

fun String.urlEncode(charset: Charset = Charsets.UTF_8): String = URLEncoder.encode(this, charset)
fun String.urlDecode(charset: Charset = Charsets.UTF_8): String = URLDecoder.decode(this, charset)

fun String.hex2Bytes(): ByteArray =
    chunked(2)
        .map { it.toInt(16).toByte() }
        .toByteArray()
