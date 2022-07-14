package net.purefunc.kotlin.ext

import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.nio.charset.StandardCharsets
import java.security.Security
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

fun String.aesEncrypt(
    transformation: String = "AES/CBC/PKCS7PADDING",
    key: String,
    iv: String,
): String =
    aes(
        transformation = transformation,
        mode = Cipher.ENCRYPT_MODE,
        key = key,
        iv = iv,
    )

fun String.aesDecrypt(
    transformation: String = "AES/CBC/PKCS7PADDING",
    key: String,
    iv: String,
): String =
    aes(
        transformation = transformation,
        mode = Cipher.DECRYPT_MODE,
        key = key,
        iv = iv,
    )

private fun String.aes(
    transformation: String,
    mode: Int,
    key: String,
    iv: String,
): String = also {
    Security.addProvider(BouncyCastleProvider())
}.let {
    Cipher.getInstance(transformation)
}.also {
    it.init(
        mode,
        SecretKeySpec(key.toByteArray(StandardCharsets.UTF_8), "AES"),
        IvParameterSpec(iv.toByteArray(StandardCharsets.UTF_8))
    )
}.let {
    if (mode == Cipher.ENCRYPT_MODE) String(Base64.getEncoder().encode(it.doFinal(this.toByteArray())))
    else String(it.doFinal(Base64.getDecoder().decode(this)))
}
