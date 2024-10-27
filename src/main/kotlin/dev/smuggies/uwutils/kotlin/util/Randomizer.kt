package dev.smuggies.uwutils.kotlin.util

/**
 * Randomizes values in a [MutableList].
 *
 * @property list the [MutableList] of values to be randomized.
 * @return The instance of this [Randomizer].
 */
@Suppress("unused")
class Randomizer<T: Any>(private val list: MutableList<T>) {

  /**
   * Generates a random [Sequence] of all values from the [MutableList].
   */
  private val randomSequence = generateSequence {
    list.shuffled().firstOrNull().also { list.remove(it) }
  }

  /**
   * @return The next value in the [MutableList] or `null` if the [MutableList] is empty.
   */
  fun next(): T? = if (list.isEmpty()) null else randomSequence.iterator().next()
}