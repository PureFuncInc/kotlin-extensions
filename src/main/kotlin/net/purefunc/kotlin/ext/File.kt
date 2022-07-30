package net.purefunc.kotlin.ext

import java.io.File
import java.io.FileOutputStream

fun String.asFile(path: String) =
    FileOutputStream(File(path))
        .let {
            it.write(toByteArray())
            it.flush()
            it.close()
        }