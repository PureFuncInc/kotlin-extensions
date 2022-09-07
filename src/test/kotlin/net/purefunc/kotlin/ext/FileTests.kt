package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Test
import java.io.File

class FileTests {

    @Test
    internal fun `test file`() {
        val content = "Hello World"

        content.toByteArray().asFile("test.txt")

        File("test.txt").delete()
    }
}
