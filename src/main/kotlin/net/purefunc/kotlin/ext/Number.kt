package net.purefunc.kotlin.ext

import java.security.SecureRandom

val random: SecureRandom
    inline get() = SecureRandom.getInstanceStrong()
