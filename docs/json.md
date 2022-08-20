* toJson

```kotlin
val userJson: String = User("Vincent", 10).toJson()
```

* toPrettyJson

```kotlin
val userJson: String = User("Vincent", 10).toPrettyJson()
```

* toClass

```kotlin
// userJson -> {"name":"Vincent","age":10}
val user: User = userJson.toClass(User::class.java)
```

* toClass

```kotlin
// userJson ->
// {
//     "name" : "Vincent",
//     "age" : 10
// }
val user: User = userJson.toClass(clazz = User::class.java, prettyJson = true)
```

* toType

```kotlin
// userJson -> {"name":"Vincent","age":10}
val user: Map<String, Any> = userJson.toType(object : TypeReference<Map<String, Any>>() {})
```

* toType

```kotlin
// userJson ->
// {
//     "name" : "Vincent",
//     "age" : 10
// }
val user: Map<String, Any> = userJson.toType(typeRef = object : TypeReference<Map<String, Any>>() {}, prettyJson = true)
```

* toMap

```kotlin
// userJson -> {"name":"Vincent","age":10}
val user: Map<String, Any> = userJson.toMap(String::class.java, Any::class.java)
```

* toMap

```kotlin
// userJson ->
// {
//     "name" : "Vincent",
//     "age" : 10
// }
val user: Map<String, Any> = userJson.toMap(k = String::class.java, v = Any::class.java, prettyJson = true)
```
