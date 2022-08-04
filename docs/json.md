* toJson

```kotlin
val userJson: String = User("Vincent", 10).toJson()
```

* toClass

```kotlin
// userJson -> {"name":"Vincent","age":10}
val user: User = userJson.toClass(User::class.java)
```

* toType

```kotlin
// userJson -> {"name":"Vincent","age":10}
val user: Map<String, Any> = userJson.toType(object : TypeReference<Map<String, Any>>() {})
```

* toMap

```kotlin
// userJson -> {"name":"Vincent","age":10}
val user: Map<String, Any> = userJson.toMap(String::class.java, Any::class.java)
```
