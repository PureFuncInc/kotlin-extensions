package net.purefunc.kotlin.ext

import net.purefunc.kotlin.arrow.AppErr

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
