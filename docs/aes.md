* aesEncrypt

```kotlin
val encryptStr: String = "Hello World".aesEncrypt(key = "1234567890123456", iv = "0123456789012345")
```

* aesDecrypt

```kotlin
val rawStr: String = encryptStr.aesDecrypt(key = "1234567890123456", iv = "0123456789012345")
```
