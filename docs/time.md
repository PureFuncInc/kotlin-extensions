* shift

```kotlin
val oneHourBefore: Long = Instant.now().toEpochMilli().shift(-1, TimeUnit.HOURS)
```
