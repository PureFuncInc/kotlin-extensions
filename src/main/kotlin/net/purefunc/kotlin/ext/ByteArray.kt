package net.purefunc.kotlin.ext

import java.util.Base64

fun ByteArray.base64Encode(): ByteArray = Base64.getEncoder().encode(this)
fun ByteArray.base64Decode(): ByteArray = Base64.getDecoder().decode(this)
fun ByteArray.bytesToHex(): String = joinToString(separator = "") { "%02x".format(it) }
fun ByteArray.string(): String = String(this)
