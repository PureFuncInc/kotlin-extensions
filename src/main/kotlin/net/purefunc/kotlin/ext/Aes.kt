package net.purefunc.kotlin.ext

import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.nio.charset.StandardCharsets
import java.security.Security
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Aes Encrypt
 *
 * @param transformation
 * @param key
 * @param iv
 *
 * @return
 */
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

/**
 * Aes Decrypt
 *
 * @param transformation
 * @param key
 * @param iv
 *
 * @return
 */
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
): String =
    also {
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
        if (mode == Cipher.ENCRYPT_MODE) String(Base64.getEncoder().encode(it.doFinal(toByteArray())))
        else String(it.doFinal(Base64.getDecoder().decode(this)))
    }
