* genUnixMilli

```kotlin
val now: Long = genUnixMilli()
```

* shift

```kotlin
val oneHourBefore: Long = now.shift(-1, TimeUnit.HOURS)
```