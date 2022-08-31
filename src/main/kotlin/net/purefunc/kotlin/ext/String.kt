package net.purefunc.kotlin.ext

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset
import java.util.Base64
import java.util.UUID
import kotlin.random.Random.Default.nextInt

val randomUUID: String
    inline get() = UUID.randomUUID().toString()

/**
 * Random Alphabetic
 *
 * @param length
 * @param spliterator
 *
 * @return
 */
fun randomAlphabetic(
    length: Int,
    spliterator: String = "",
): String =
    (1..length)
        .map { nextInt(0, alphabetic.size) }
        .map { alphabetic[it] }
        .joinToString(spliterator)

/**
 * Random Alphanumeric
 *
 * @param length
 * @param spliterator
 *
 * @return
 */
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

/**
 * Encode Base64
 *
 * @param charset
 *
 * @return
 */
fun ByteArray.encodeBase64(
    charset: Charset = Charsets.UTF_8
): String =
    String(Base64.getEncoder().encode(this), charset)

/**
 * Base64 Decode
 *
 * @param charset
 *
 * @return
 */
fun String.base64Decode(
    charset: Charset = Charsets.UTF_8
): String =
    String(Base64.getDecoder().decode(toByteArray()), charset)

/**
 * Url Encode
 *
 * @param charset
 *
 * @return
 */
fun String.urlEncode(
    charset: Charset = Charsets.UTF_8
): String =
    URLEncoder.encode(this, charset)

/**
 * Url Decode
 *
 * @param charset
 *
 * @return
 */
fun String.urlDecode(
    charset: Charset = Charsets.UTF_8
): String =
    URLDecoder.decode(this, charset)
