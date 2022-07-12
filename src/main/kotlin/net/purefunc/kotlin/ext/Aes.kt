package net.purefunc.kotlin.ext

import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.Security
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

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