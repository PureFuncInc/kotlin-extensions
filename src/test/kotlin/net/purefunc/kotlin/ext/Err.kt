package net.purefunc.kotlin.ext

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