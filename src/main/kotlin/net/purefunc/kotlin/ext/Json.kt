package net.purefunc.kotlin.ext

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

private val objectMapper = jacksonObjectMapper()

fun Any.toJson() = objectMapper.writeValueAsString(this)

fun <K, V> String.toMap(
    k: Class<K>,
    v: Class<V>,
) = objectMapper.readValue(this, object : TypeReference<Map<K, V>>() {})

infix fun <T> String.to(t: Class<T>) = objectMapper.readValue(this, t)

infix fun <T> String.to(t: TypeReference<T>) = objectMapper.readValue(this, t)