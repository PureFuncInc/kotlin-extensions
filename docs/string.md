* randomAlphabetic

```kotlin
val randomAlphabetic: String = randomAlphabetic(32)
```

* randomAlphanumeric

```kotlin
val randomAlphanumeric: String = randomAlphanumeric(32)
```

* urlEncode & urlDecode

```kotlin
val encodedUrl: String = "https://purefunc.net/Hello World.jpg".urlEncode()
val rawUrl: String = encodedUrl.urlDecode()

// rawUrl should be "https://purefunc.net/Hello World.jpg"
```
