package net.purefunc.kotlin.ext

import java.util.Base64

fun ByteArray.base64Encode(): ByteArray = Base64.getEncoder().encode(this)
fun ByteArray.base64Decode(): ByteArray = Base64.getDecoder().decode(this)
