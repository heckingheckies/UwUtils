package dev.smuggies.uwutils.kotlin.util

/**
 * A class for randomizing values in a [MutableList].
 *
 * @property list the [MutableList] of values to be randomised.
 * @return The instance of this [Randomiser].
 */
@Suppress("unused")
class Randomiser<T: Any>(private val list: MutableList<T>) {

  /**
   * A random [Sequence] of all values from the [MutableList].
   */
  private val randomSequence = generateSequence {
    list.shuffled().firstOrNull()?.also { list.remove(it) }
  }

  /**
   * @return The next value in the [MutableList] or `null` if the [MutableList] is empty.
   */
  fun next(): T? = if (list.isEmpty()) null else randomSequence.iterator().next()
}