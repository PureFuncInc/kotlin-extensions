package net.purefunc.kotlin.ext

import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.MessageDigest
import java.security.Security
import java.util.Base64
import java.util.UUID
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import kotlin.random.Random.Default.nextInt

val randomUUID: String inline get() = UUID.randomUUID().toString()

fun randomAlphanumeric(length: Int) =
    (1..length)
        .map { nextInt(0, 62) }
        .map((('a'..'z') + ('A'..'Z') + ('0'..'9'))::get)
        .joinToString("")

fun ByteArray.encodeBase64() = String(Base64.getEncoder().encode(this))
fun String.base64Decode() = String(Base64.getDecoder().decode(this.toByteArray()))

sealed class Transformation(
    val value: String,
) {
    object AES_CBC_PKCS7PADDING : Transformation("AES/CBC/PKCS7PADDING")
}

data class AesKeyPair(
    private val key: String,
    private val iv: String,
) {
    companion object {
        fun create(key: String, iv: String) =
            apply {
                if (key.length != 16 || iv.length != 16) throw RuntimeException()
            }.run {
                AesKeyPair(key, iv)
            }
    }

    fun getKeySpec() = SecretKeySpec(key.toByteArray(), "AES")
    fun getIvSpec() = IvParameterSpec(key.toByteArray())
}

fun String.aesEncrypt(
    transformation: Transformation = Transformation.AES_CBC_PKCS7PADDING,
    aesKeyPair: AesKeyPair,
): String = this.aes(transformation = transformation, mode = Cipher.ENCRYPT_MODE, aesKeyPair = aesKeyPair)

fun String.aesDecrypt(
    transformation: Transformation = Transformation.AES_CBC_PKCS7PADDING,
    aesKeyPair: AesKeyPair,
): String = this.aes(transformation = transformation, mode = Cipher.DECRYPT_MODE, aesKeyPair = aesKeyPair)

private fun String.aes(
    transformation: Transformation,
    mode: Int,
    aesKeyPair: AesKeyPair,
): String = also {
    Security.addProvider(BouncyCastleProvider())
}.let {
    Cipher.getInstance(transformation.value)
}.also {
    it.init(mode, aesKeyPair.getKeySpec(), aesKeyPair.getIvSpec())
}.let {
    if (mode == Cipher.ENCRYPT_MODE) String(Base64.getEncoder().encode(it.doFinal(this.toByteArray())))
    else String(it.doFinal(Base64.getDecoder().decode(this)))
}

fun String.md5(): String = this hashBy MD5
fun String.sha3(bits: Int = 512): String =
    when (bits) {
        256 -> this hashBy SHA3_256
        else -> this hashBy SHA3_512
    }

private infix fun String.hashBy(shaAlgorithm: ShaAlgorithm): String =
    MessageDigest.getInstance(shaAlgorithm.value)
        .also {
            it.update(toByteArray())
        }.run {
            String(Base64.getEncoder().encode(this.digest()))
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