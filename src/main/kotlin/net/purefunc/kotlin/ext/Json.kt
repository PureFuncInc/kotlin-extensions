package net.purefunc.kotlin.ext

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

private val objectMapper: ObjectMapper = jacksonObjectMapper()

private val prettyObjectMapper: ObjectMapper = jacksonObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)

/**
 * To Json
 *
 * @return
 */
fun Any.toJson(): String = objectMapper.writeValueAsString(this)

/**
 * To Pretty Json
 *
 * @return
 */
fun Any.toPrettyJson(): String = prettyObjectMapper.writeValueAsString(this)

/**
 * To Map
 *
 * @param K
 * @param V
 * @param k
 * @param v
 * @param prettyJson
 *
 * @return
 */
fun <K, V> String.toMap(
    k: Class<K>,
    v: Class<V>,
    prettyJson: Boolean = false,
): Map<K, V> =
    if (prettyJson) prettyObjectMapper.readValue(this, object : TypeReference<Map<K, V>>() {})
    else objectMapper.readValue(this, object : TypeReference<Map<K, V>>() {})

/**
 * To Class
 *
 * @param T
 * @param clazz
 * @param prettyJson
 *
 * @return
 */
fun <T> String.toClass(
    clazz: Class<T>,
    prettyJson: Boolean = false,
): T =
    if (prettyJson) prettyObjectMapper.readValue(this, clazz)
    else objectMapper.readValue(this, clazz)

/**
 * To Type
 *
 * @param T
 * @param typeRef
 * @param prettyJson
 *
 * @return
 */
fun <T> String.toType(
    typeRef: TypeReference<T>,
    prettyJson: Boolean = false,
): T =
    if (prettyJson) prettyObjectMapper.readValue(this, typeRef)
    else objectMapper.readValue(this, typeRef)
