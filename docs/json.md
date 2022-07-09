* toJson

```kotlin
val userJson: String = User("Vincent", 10).toJson()
```

* to

```kotlin
// userJson -> {"name":"Vincent","age":10}
val user: User = userJson.to(User::class.java)
val user: User = userJson to User::class.java
```

* to

```kotlin
// userJson -> {"name":"Vincent","age":10}
val user: Map<String, Any> = userJson.to(object : TypeReference<Map<String, Any>>() {})
val user: Map<String, Any> = userJson to object : TypeReference<Map<String, Any>>() {}
```

* toMap

```kotlin
// userJson -> {"name":"Vincent","age":10}
val user: Map<String, Any> = userJson.toMap(String::class.java, Any::class.java)
```

