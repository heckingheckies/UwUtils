package dev.smuggies.uwutils.kotlin.extensions

/**
 * Adds all the specified [values] to the [MutableList].
 * @param values The [values] to add.
 */
@Suppress("unused")
fun <T> MutableList<T>.addAll(vararg values: T) = values.forEach { add(it) }

/**
 * Removes all the specified [values] from the [MutableList].
 * @param values The [values] to remove.
 */
@Suppress("unused")
fun <T> MutableList<T>.removeAll(vararg values: T) = values.forEach { remove(it) }