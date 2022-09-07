package net.purefunc.kotlin.ext

import java.io.File
import java.io.FileOutputStream

/**
 * As File
 *
 * @param path
 */
fun ByteArray.asFile(path: String) =
    FileOutputStream(File(path))
        .let {
            it.write(this)
            it.flush()
            it.close()
        }
