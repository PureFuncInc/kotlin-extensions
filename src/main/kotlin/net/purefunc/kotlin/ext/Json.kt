package net.purefunc.kotlin.ext

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

private val objectMapper: ObjectMapper = jacksonObjectMapper()

private val prettyObjectMapper: ObjectMapper = jacksonObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)

fun Any.toJson(): String = objectMapper.writeValueAsString(this)

fun Any.toPrettyJson(): String = prettyObjectMapper.writeValueAsString(this)

fun <K, V> String.toMap(
    k: Class<K>,
    v: Class<V>,
    prettyJson: Boolean = false,
): Map<K, V> =
    if (prettyJson) prettyObjectMapper.readValue(this, object : TypeReference<Map<K, V>>() {})
    else objectMapper.readValue(this, object : TypeReference<Map<K, V>>() {})

fun <T> String.toClass(
    clazz: Class<T>,
    prettyJson: Boolean = false,
): T =
    if (prettyJson) prettyObjectMapper.readValue(this, clazz)
    else objectMapper.readValue(this, clazz)


fun <T> String.toType(
    typeRef: TypeReference<T>,
    prettyJson: Boolean = false,
): T =
    if (prettyJson) prettyObjectMapper.readValue(this, typeRef)
    else objectMapper.readValue(this, typeRef)
