package net.purefunc.kotlin.ext

import java.util.UUID
import kotlin.random.Random.Default.nextInt

fun randomUUID() = UUID.randomUUID().toString()

fun randomAlphanumeric(length: Int) =
    (1..length)
        .map { nextInt(0, 62) }
        .map((('a'..'z') + ('A'..'Z') + ('0'..'9'))::get)
        .joinToString("")
