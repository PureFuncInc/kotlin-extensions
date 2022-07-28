package net.purefunc.kotlin.ext

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset
import java.util.Base64
import java.util.UUID
import kotlin.random.Random.Default.nextInt

val randomUUID: String
    inline get() = UUID.randomUUID().toString()

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

private val alphabetic: List<Char> = ('a'..'z') + ('A'..'Z')
private val alphanumerics: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

fun ByteArray.encodeBase64(charset: Charset = Charsets.UTF_8): String =
    String(Base64.getEncoder().encode(this), charset)

fun String.base64Decode(charset: Charset = Charsets.UTF_8): String =
    String(Base64.getDecoder().decode(this.toByteArray()), charset)

fun String.urlEncode(charset: Charset = Charsets.UTF_8) = URLEncoder.encode(this, charset)
fun String.urlDecode(charset: Charset = Charsets.UTF_8) = URLDecoder.decode(this, charset)
