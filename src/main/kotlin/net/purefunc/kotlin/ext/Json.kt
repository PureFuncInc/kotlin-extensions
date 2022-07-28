package net.purefunc.kotlin.ext

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

private val objectMapper: ObjectMapper = jacksonObjectMapper()

fun Any.toJson(): String = objectMapper.writeValueAsString(this)

fun <K, V> String.toMap(
    k: Class<K>,
    v: Class<V>,
): Map<K, V> =
    objectMapper.readValue(
        this,
        object : TypeReference<Map<K, V>>() {},
    )

fun <T> String.toClass(t: Class<T>): T = objectMapper.readValue(this, t)

fun <T> String.toType(t: TypeReference<T>): T = objectMapper.readValue(this, t)