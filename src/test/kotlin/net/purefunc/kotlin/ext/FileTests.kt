package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.File

class FileTests {

    @Test
    internal fun `test dump file`() {
        val rawString = "Hello World"

        rawString
            .toByteArray()
            .dumpFile("./test.dat")

        val file = File("./test.dat")
        file.forEachLine {
            Assertions.assertEquals(rawString, it)
        }

        file.delete()
    }
}
