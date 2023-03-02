* isPositive & isNotPositive

```kotlin
val result: Boolean = 2L.isPositive()
val result: Boolean = 2.isPositive()
val result: Boolean = 2F.isPositive()
val result: Boolean = 2.0.isPositive()
val result: Boolean = BigDecimal("2.0").isPositive()
val result: Boolean = 0L.isNotPositive()
val result: Boolean = 0.isNotPositive()
val result: Boolean = 0F.isNotPositive()
val result: Boolean = 0.0.isNotPositive()
val result: Boolean = BigDecimal("0.0").isNotPositive()
```

* isZero

```kotlin
val result: Boolean = 0L.isZero()
val result: Boolean = 0.isZero()
val result: Boolean = 0F.isZero()
val result: Boolean = 0.0.isZero()
val result: Boolean = BigDecimal("0.0").isZero()
```

* isNegative & isNotNegative

```kotlin
val result: Boolean = (-2L).isNegative()
val result: Boolean = (-2).isNegative()
val result: Boolean = (-2F).isNegative()
val result: Boolean = (-2.0).isNegative()
val result: Boolean = BigDecimal("-2.0").isNegative()
val result: Boolean = 0L.isNotNegative()
val result: Boolean = 0.isNotNegative()
val result: Boolean = 0F.isNotNegative()
val result: Boolean = 0.0.isNotNegative()
val result: Boolean = BigDecimal("0.0").isNotNegative()
```

* isOdd & isEven

```kotlin
val result: Boolean = 1L.isOdd()
val result: Boolean = 2L.isEven()
val result: Boolean = 1.isOdd()
val result: Boolean = 2.isEven()
```
