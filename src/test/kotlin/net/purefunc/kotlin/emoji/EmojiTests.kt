package net.purefunc.kotlin.emoji

import net.purefunc.kotlin.ext.Slf4j.Companion.log
import org.junit.jupiter.api.Test

class EmojiTests {

    @Test
    internal fun `println emoji`() {
        Emoji0.values().forEach {
            log.info(it.toString())
        }
        Emoji1.values().forEach {
            log.info(it.toString())
        }
        Emoji2.values().forEach {
            log.info(it.toString())
        }
        Emoji3.values().forEach {
            log.info(it.toString())
        }
    }
}