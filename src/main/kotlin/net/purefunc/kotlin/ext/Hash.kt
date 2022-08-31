package net.purefunc.kotlin.ext

import at.favre.lib.crypto.bcrypt.BCrypt
import java.security.MessageDigest
import java.util.Base64

/**
 * MD5
 *
 * @return
 */
fun String.md5(): String = this hashBy MD5

/**
 * Bcrypt
 *
 * @param cost
 *
 * @return
 */
fun String.bcrypt(cost: Int = 12): String =
    when (cost) {
        10 -> this hashBy BCRYPT_10
        else -> this hashBy BCRYPT_12
    }

/**
 * SHA3
 *
 * @param bits
 *
 * @return
 */
fun String.sha3(bits: Int = 512): String =
    when (bits) {
        256 -> this hashBy SHA3_256
        else -> this hashBy SHA3_512
    }

private infix fun String.hashBy(
    shaAlgorithm: ShaAlgorithm,
): String =
    when (shaAlgorithm) {
        BCRYPT_10 -> BCrypt.withDefaults().hashToString(10, toCharArray())
        BCRYPT_12 -> BCrypt.withDefaults().hashToString(12, toCharArray())
        else ->
            run {
                MessageDigest.getInstance(shaAlgorithm.value)
                    .also {
                        it.update(toByteArray())
                    }.run {
                        String(Base64.getEncoder().encode(digest()))
                    }
            }
    }

sealed class ShaAlgorithm(
    val value: String,
) {
    object MD5 : ShaAlgorithm("MD5")
    object BCRYPT_10 : ShaAlgorithm("BCRYPT-10")
    object BCRYPT_12 : ShaAlgorithm("BCRYPT-12")
    object SHA3_256 : ShaAlgorithm("SHA3-256")
    object SHA3_512 : ShaAlgorithm("SHA3-512")
}

typealias MD5 = ShaAlgorithm.MD5
typealias BCRYPT_10 = ShaAlgorithm.BCRYPT_10
typealias BCRYPT_12 = ShaAlgorithm.BCRYPT_12
typealias SHA3_256 = ShaAlgorithm.SHA3_256
typealias SHA3_512 = ShaAlgorithm.SHA3_512
