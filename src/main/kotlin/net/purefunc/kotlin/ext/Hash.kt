package net.purefunc.kotlin.ext

import java.security.MessageDigest
import java.util.Base64

fun String.md5(): String = this hashBy MD5
fun String.sha3(bits: Int = 512): String =
    when (bits) {
        256 -> this hashBy SHA3_256
        else -> this hashBy SHA3_512
    }

private infix fun String.hashBy(
    shaAlgorithm: ShaAlgorithm,
): String =
    MessageDigest.getInstance(shaAlgorithm.value)
        .also {
            it.update(toByteArray())
        }.run {
            String(Base64.getEncoder().encode(digest()))
        }

sealed class ShaAlgorithm(
    val value: String,
) {

    object MD5 : ShaAlgorithm("MD5")
    object SHA3_256 : ShaAlgorithm("SHA3-256")
    object SHA3_512 : ShaAlgorithm("SHA3-512")
}

typealias MD5 = ShaAlgorithm.MD5
typealias SHA3_256 = ShaAlgorithm.SHA3_256
typealias SHA3_512 = ShaAlgorithm.SHA3_512
