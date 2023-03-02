* base64Encode & base64Decode

```kotlin
val base64EncodedResult: String = "Hello World".toByteArray().base64Encode().bytesToHex()
val rawStr: String = base64EncodedResult.hex2Bytes().base64Decode().run { String(this) }

// rawStr should be "Hello World"
```
