# Generic Type

* R imply Either Right
* L imply Either Left
* T imply Return Value Type

# Err

* AppErr

```kotlin
sealed class TestErr {

    object NullEntry : AppErr("E500001", "Null")
    object Assert : AppErr("E500002", "Assert")
    object OutBound : AppErr("E500003", "Out Bound")
    object CastClass : AppErr("E500004", "Cast Class")
}

typealias NullErr = TestErr.NullEntry
typealias AssertErr = TestErr.Assert
typealias OutBoundErr = TestErr.OutBound
typealias CastClassErr = TestErr.CastClass
```

# catchErr

* catchErrWhenNull
* catchErrWhenTrue
* catchErrWhenFalse
* catchErrWhenApply
* catchErrWhenRun
* flatCatchErrWhenNull
* flatCatchErrWhenTrue
* flatCatchErrWhenFalse
* flatCatchErrWhenApply
* flatCatchErrWhenRun
* zipAllEither

```kotlin
val list = list("value1")
val map = mapOf("key1", "value1")

val either: Either<AppErr, String> = map["key2"].catchErrWhenNull(NullErr)
val either: Either<AppErr, String?> = map["key1"].catchErrWhenTrue(AssertErr) { it!!.length > 3 }
val either: Either<AppErr, String?> = map["key1"].catchErrWhenFalse(AssertErr) { it!!.length < 3 }
val either: Either<AppErr, List<String>> = list.catchErrWhenApply(OutBoundErr) { it[100] }
val either: Either<AppErr, Map<*, *>> = list.catchErrWhenRun(CastClassErr) { it as Map<*, *> }

val eitherLeft: Either<AppErr, Map<*, *>> =
    map["key1"]
        .catchErrWhenTrue(AssertErr) { it == null }
        .flatCatchErrWhenNull(NullErr)
        .flatCatchErrWhenTrue(AssertErr) { it.length < 3 }
        .flatCatchErrWhenFalse(AssertErr) { it.length > 3 }
        .flatCatchErrWhenApply(OutBoundErr) { listOf(it)[0] }
        .flatCatchErrWhenRun(CastClassErr) { listOf(it) as Map<*, *> }
Assertions.assertEquals("E500004", eitherLeft.assertEitherLeft().code)
Assertions.assertEquals("Cast Class", eitherLeft.assertEitherLeft().message)

val eitherRight: Either<AppErr, List<*>> =
    map["key1"]
        .catchErrWhenTrue(AssertErr) { it == null }
        .flatCatchErrWhenNull(NullErr)
        .flatCatchErrWhenTrue(AssertErr) { it.length < 3 }
        .flatCatchErrWhenFalse(AssertErr) { it.length > 3 }
        .flatCatchErrWhenApply(OutBoundErr) { listOf(it)[0] }
        .flatCatchErrWhenRun(CastClassErr) { listOf(it) as MutableList<*> }
Assertions.assertEquals(list.toMutableList(), eitherRight.assertEitherRight())

val eitherAllRight: Either<AppErr, List<*>> =
    zipAllEither(
        eitherRightNull,
        eitherRightTrue,
        eitherRightFalse,
        eitherRightApply,
        eitherRightRun,
    )
Assertions.assertEquals(5, eitherAllRight.assertEitherRight().size)

val eitherAllLeftRun: Either<AppErr, List<*>> =
    zipAllEither(
        eitherRightNull,
        eitherRightTrue,
        eitherRightFalse,
        eitherRightApply,
        eitherLeftRun,
    )
Assertions.assertEquals("E500004", eitherAllLeftRun.assertEitherLeft().code)
Assertions.assertEquals("Cast Class", eitherAllLeftRun.assertEitherLeft().message)
```

# validErr

* validErrWhenNull
* validErrWhenTrue
* validErrWhenFalse
* validErrWhenApply
* validErrWhenRun
* flatValidErrWhenNull
* flatValidErrWhenTrue
* flatValidErrWhenFalse
* flatValidErrWhenApply
* flatValidErrWhenRun
* zipAllValid

```kotlin
val list = listOf("value1")
val map = mapOf("key1" to "value1")

val validatedLeftNull: ValidatedNel<NullErr, String> = map["key2"].validErrWhenNull(NullErr)
val validatedLeftTrue: ValidatedNel<AssertErr, String?> = map["key1"].validErrWhenTrue(AssertErr) { it!!.length > 3 }
val validatedLeftFalse: ValidatedNel<AssertErr, String?> = map["key1"].validErrWhenFalse(AssertErr) { it!!.length < 3 }
val validatedLeftApply: ValidatedNel<OutBoundErr, List<String>> = list.validErrWhenApply(OutBoundErr) { it[100] }
val validatedLeftRun: ValidatedNel<CastClassErr, Map<*, *>> = list.validErrWhenRun(CastClassErr) { it as Map<*, *> }
val validatedRightNull: ValidatedNel<NullErr, String> = map["key1"].validErrWhenNull(NullErr)
val validatedRightTrue: ValidatedNel<AssertErr, String?> = map["key1"].validErrWhenTrue(AssertErr) { it!!.length < 3 }
val validatedRightFalse: ValidatedNel<AssertErr, String?> = map["key1"].validErrWhenFalse(AssertErr) { it!!.length > 3 }
val validatedRightApply: ValidatedNel<OutBoundErr, List<String>> = list.validErrWhenApply(OutBoundErr) { it[0] }
val validatedRightRun: ValidatedNel<CastClassErr, MutableList<String>> = list.validErrWhenRun(CastClassErr) { it as MutableList<String> }

val zipAllValidLeft: EitherNel<AppErr, List<*>> =
    zipAllValid(
        validatedLeftNull,
        validatedLeftTrue,
        validatedLeftFalse,
        validatedLeftApply,
        validatedLeftRun,
    )
Assertions.assertEquals(5, zipAllValidLeft.assertEitherLeft().size)

val zipAllValidRight: EitherNel<AppErr, List<*>> =
    zipAllValid(
        validatedRightNull,
        validatedRightTrue,
        validatedRightFalse,
        validatedRightApply,
        validatedRightRun,
    )
Assertions.assertIterableEquals(listOf("value1", "value1", "value", list, list), zipAllValidRight.assertEitherRight())

val eitherValidLeftNull: EitherNel<AppErr, String> =
    zipAllValid(
        validatedRightNull,
        validatedRightTrue,
        validatedRightFalse,
        validatedRightApply,
        validatedRightRun,
    )
        .flatValidErrWhenNull(NullErr)
        .flatValidErrWhenTrue(AssertErr) { it.size < 2 }
        .flatValidErrWhenFalse(AssertErr) { it.size > 2 }
        .flatValidErrWhenApply(OutBoundErr) { it[2] }
        .flatValidErrWhenRun(CastClassErr) { map[it[0]] }
        .flatValidErrWhenNull(NullErr) // <- fail here
Assertions.assertEquals(1, eitherValidLeftNull.assertEitherLeft().size)
Assertions.assertEquals("E500001", eitherValidLeftNull.assertEitherLeft()[0].code)
Assertions.assertEquals("Null", eitherValidLeftNull.assertEitherLeft()[0].message)
```
