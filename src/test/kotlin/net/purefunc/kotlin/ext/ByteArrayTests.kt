package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ByteArrayTests {

    /**
     * raw string -> toByteArray -> base64 encode -> bytes to hex -> hex to bytes -> base64 decode -> to string
     */
    @Test
    internal fun `test base64 encode & decode`() {
        val rawString = "Hello World"

        val base64EncodedResult: String = rawString.toByteArray().base64Encode().bytesToHex()
        val base64DecodedResult: String = base64EncodedResult.hex2Bytes().base64Decode().string()

        Assertions.assertEquals(rawString, base64DecodedResult)
    }
}
