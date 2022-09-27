package net.purefunc.kotlin.ext

import java.io.File
import java.io.FileOutputStream

fun ByteArray.dumpFile(path: String): String =
    FileOutputStream(File(path))
        .let {
            it.write(this)
            it.flush()
            path
        }
