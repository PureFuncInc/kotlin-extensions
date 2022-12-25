package net.purefunc.kotlin.ext

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset
import java.util.*
import kotlin.random.Random.Default.nextInt

val randomUUID: String
    inline get() = UUID.randomUUID().toString()

private val alphabetic: List<Char> = ('a'..'z') + ('A'..'Z')
private val alphanumerics: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

fun randomAlphabetic(
    length: Int,
    spliterator: String = "",
): String =
    (1..length)
        .map { nextInt(0, alphabetic.size) }
        .map { alphabetic[it] }
        .joinToString(spliterator)

fun randomAlphanumeric(
    length: Int,
    spliterator: String = "",
): String =
    (1..length)
        .map { nextInt(0, alphanumerics.size) }
        .map { alphanumerics[it] }
        .joinToString(spliterator)

fun ByteArray.encodeBase64(): ByteArray = Base64.getEncoder().encode(this)
fun ByteArray.base64Decode(): ByteArray = Base64.getDecoder().decode(this)
fun ByteArray.string(charset: Charset = Charsets.UTF_8): String = String(this, charset)

fun String.urlEncode(charset: Charset = Charsets.UTF_8): String = URLEncoder.encode(this, charset)
fun String.urlDecode(charset: Charset = Charsets.UTF_8): String = URLDecoder.decode(this, charset)
