package net.purefunc.kotlin.ext

import com.fasterxml.jackson.core.type.TypeReference
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class JsonTests {

    data class User(
        val name: String,
        val age: Int,
    )

    @Test
    internal fun `test json object`() {
        val user = User("Vincent", 10)

        Assertions.assertEquals(user, user.toJson() to User::class.java)
    }

    @Test
    internal fun `test collection`() {
        val map = mapOf("name" to "Vincent", "age" to 18)

        Assertions.assertEquals(map, map.toJson() to object : TypeReference<Map<String, Any>>() {})
        Assertions.assertEquals(map, map.toJson().toMap(String::class.java, Any::class.java))
    }
}