package net.purefunc.kotlin.emoji

import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.Locale

fun main() {
    val lines = mutableListOf<String>()
    var flag = false

    // collect emoji lines
    // 1F636 200D 1F32B FE0F                                  ; fully-qualified     # πΆβπ«οΈ E13.1 face in clouds
    URL("https://unicode.org/Public/emoji/14.0/emoji-test.txt")
        .openStream()
        .bufferedReader()
        .useLines { readLine ->
            readLine.forEach {
                if (it == "") flag = false
                if (flag) lines.add(it)
                if (it.startsWith("# subgroup: ")) flag = true
            }
        }

    val bigEnum = lines.filter {
        it.contains("fully-qualified")
    }.map {
        it.split(" ")
    }.map { elements ->
        // [1F636, 200D, 1F32B, FE0F, , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , , ;, fully-qualified, , , , , #, πΆβπ«οΈ, E13.1, face, in, cloud]

        val items = mutableListOf<String>()

        val codePartIdxs = elements.mapIndexed { idx, element -> if (element == "" || element == ";") idx else -1 }
        val codeIdx = codePartIdxs.filter { idx -> idx != -1 }[0]

        (0 until codeIdx).forEach { i -> items.add(elements[i]) }
        items.add(";")

        val namePartIdxs = elements.mapIndexed { idx, element ->
            if (element.startsWith("E") && element.contains(".")) idx
            else -1
        }
        val nameIdx = namePartIdxs.filter { idx -> idx != -1 }[0]

        items.addAll(
            // replace item name contains invalid char
            elements.subList(nameIdx + 1, elements.size).map { str ->
                str.replace("β", "")
                    .replace("β", "")
                    .replace("β", "")
                    .replace("-", "_")
                    .replace(":", "")
                    .replace(".", "")
                    .replace("!", "")
                    .replace("(", "")
                    .replace(")", "")
                    .replace("1st", "first")
                    .replace("2nd", "second")
                    .replace("3rd", "third")
                    .replace("package", "packages")
                    .replace("#", "hash")
                    .replace("*", "star")
                    .replace(",", "comma")
                    .replace("&", "and")
            }
        )

        items
    }

    // all enum in one .kt is will exceed jvm limit 64K
    val pageCount = 1000
    val pageSize = (bigEnum.size / pageCount)
    val pagingItems = (0..pageSize).map { page ->
        if (pageCount * (page + 1) > bigEnum.size) {
            bigEnum.subList(pageCount * page, bigEnum.size)
        } else {
            bigEnum.subList(pageCount * page, pageCount * (page + 1))
        }
    }

    pagingItems.forEachIndexed { fileIdx, item ->
        val fos = FileOutputStream(File("src/main/kotlin/net/purefunc/kotlin/emoji/Emoji$fileIdx.kt"))
        fos.write(
            """
            package net.purefunc.kotlin.emoji
            
            enum class Emoji$fileIdx(
                private val intArray: IntArray,
            ) {
            
        """.trimIndent().toByteArray()
        )

        item.forEachIndexed { idx, element ->
            val idxListThird = element.mapIndexed { i, e -> if (e == ";") i else -1 }
            val splitIdx = idxListThird.filter { i -> i != -1 }[0]

            val name = element.subList(splitIdx + 1, element.size).joinToString("_").uppercase(Locale.getDefault())
            val hexs = element.subList(0, splitIdx).map { hex -> "0x$hex" }.joinToString(",")
            val emojiIntArr = element.subList(0, splitIdx).map { hex -> hex.toInt(16) }.toIntArray()
            val emoji = String(emojiIntArr, 0, emojiIntArr.size)

            val comment = "    // $emoji $emoji $emoji"
            val enum = "    $name(intArrayOf($hexs))"
            if (idx == item.size - 1) {
                fos.write("$comment\n$enum;\n".toByteArray())
            } else {
                fos.write("$comment\n$enum,\n".toByteArray())
            }
        }

        fos.write(
            """
            
                override fun toString() = String(intArray, 0, intArray.size)
            }
            
        """.trimIndent().toByteArray()
        )
    }
}