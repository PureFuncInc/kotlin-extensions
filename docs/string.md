* randomUUID

```kotlin
val userJson: String = User("Vincent", 10).toJson()
```

* randomAlphanumeric

```kotlin
val randomAlpha: String = randomAlphanumeric(32)
```

* encodeBase64

```kotlin
val base64EncodedStr: String = "Hello World".toByteArray().encodeBase64() 
```

* base64Decode

```kotlin
val originStr: String = base64EncodedStr.base64Decode()
```