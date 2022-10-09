package net.purefunc.kotlin.arrow

sealed class TestErr {

    object Type1 : AppErr("E500001", "Type1")
    object Type2 : AppErr("E500002", "Type2")
    object Type3 : AppErr("E500003", "Type3")
    object Type4 : AppErr("E500004", "Type4")
}

typealias Type1Err = TestErr.Type1
typealias Type2Err = TestErr.Type2
typealias Type3Err = TestErr.Type3
typealias Type4Err = TestErr.Type4
