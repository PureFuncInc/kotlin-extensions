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
val base64EncodedStr: String = "Hello World".toByteArray().encodeBase64() 
```

* base64Decode

```kotlin
val originStr: String = base64EncodedStr.base64Decode()
```

* urlEncode

```kotlin
val encodedUrl: String = "https://purefunc.net/Hello World.jpg".urlEncode()
```

* urlDecode

```kotlin
val decodedUrl: String = encodedUrl.decodeUrl()
```