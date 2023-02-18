package net.purefunc.kotlin.ext

import java.security.SecureRandom

/**
 * Returns a random element from this collection using the specified source of randomness.
 *
 * @throws NoSuchElementException if this collection is empty.
 *
 * @see Collection.random
 */
infix fun <T> Collection<T>.randomGetWith(secureRandom: SecureRandom): T =
    takeIf { it.isNotEmpty() }
        ?.run { elementAt(secureRandom.nextInt(size)) }
        ?: throw NoSuchElementException("Collection is empty.")
