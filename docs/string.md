* randomUUID

```kotlin
val randomUUID: String = randomUUID
```

* randomAlphabetic

```kotlin
val randomAlphabetic: String = randomAlphabetic(32)
```

* randomAlphanumeric

```kotlin
val randomAlphanumeric: String = randomAlphanumeric(32)
```

* encodeBase64

```kotlin
val originStr = "Hello World"
val base64Encoded: ByteArray = originStr.toByteArray().encodeBase64()
```

* base64Decode

```kotlin
// "Hello World"
val originStr: String = base64Encoded.base64Decode().string()
```

* urlEncode

```kotlin
val encodedUrl: String = "https://purefunc.net/Hello World.jpg".urlEncode()
```

* urlDecode

```kotlin
val decodedUrl: String = encodedUrl.decodeUrl()
```
