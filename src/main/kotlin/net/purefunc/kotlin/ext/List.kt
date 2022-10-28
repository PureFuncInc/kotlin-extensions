package net.purefunc.kotlin.ext

import java.security.SecureRandom

/**
 * Returns a random element from this collection using the specified source of randomness.
 *
 * @throws NoSuchElementException if this collection is empty.
 *
 * @see Collection.random
 */
fun <T> Collection<T>.randomGet(random: SecureRandom): T =
    takeIf { it.isNotEmpty() }
        ?.run { elementAt(random.nextInt(size)) }
        ?: throw NoSuchElementException("Collection is empty.")