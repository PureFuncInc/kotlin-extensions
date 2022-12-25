package net.purefunc.kotlin.ext

import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

val unixTime: Long
    inline get() = Instant.now().epochSecond
val unixTimeMilli: Long
    inline get() = Instant.now().toEpochMilli()

fun Long.shift(
    delta: Long,
    timeUnit: TimeUnit,
): Long =
    when (timeUnit) {
        TimeUnit.MILLISECONDS -> this + delta
        TimeUnit.SECONDS -> this + (delta * 1000)
        TimeUnit.MINUTES -> this + (delta * 60 * 1000)
        TimeUnit.HOURS -> this + (delta * 60 * 60 * 1000)
        TimeUnit.DAYS -> this + (delta * 24 * 60 * 60 * 1000)
        else -> throw RuntimeException("Unsupported TimeUnit: $timeUnit")
    }

fun OffsetDateTime.isoString(): String = DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this)
fun LocalDateTime.isoString(): String = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(this)
fun LocalTime.isoString(): String = DateTimeFormatter.ISO_LOCAL_TIME.format(this)

fun String.toOffsetDateTime(): OffsetDateTime = OffsetDateTime.parse(this, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
fun String.toLocalDateTime(): LocalDateTime = LocalDateTime.parse(this, DateTimeFormatter.ISO_LOCAL_DATE_TIME)
fun String.toLocalTime(): LocalTime = LocalTime.parse(this, DateTimeFormatter.ISO_LOCAL_TIME)
