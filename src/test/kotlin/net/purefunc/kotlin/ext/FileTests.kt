package net.purefunc.kotlin.ext

import org.junit.jupiter.api.Test
import java.io.File

class FileTests {

    @Test
    internal fun `test hash`() {
        val content = "Hello World"

        content.asFile("test.txt")

        File("test.txt").delete()
    }
}