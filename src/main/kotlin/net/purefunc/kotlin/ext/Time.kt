package net.purefunc.kotlin.ext

import java.time.Instant
import java.util.concurrent.TimeUnit

val unixTimeMilli: Long inline get() = Instant.now().toEpochMilli()

fun Long.shift(delta: Long, timeUnit: TimeUnit) = when (timeUnit) {
    TimeUnit.MILLISECONDS -> this + delta
    TimeUnit.SECONDS -> this + (delta * 1000)
    TimeUnit.MINUTES -> this + (delta * 60 * 1000)
    TimeUnit.HOURS -> this + (delta * 60 * 60 * 1000)
    TimeUnit.DAYS -> this + (delta * 24 * 60 * 60 * 1000)
    else -> throw RuntimeException("Unsupported TimeUnit: $timeUnit")
}