package dev.smuggies.uwutils.kotlin

import java.util.concurrent.ThreadLocalRandom
import kotlin.math.pow
import kotlin.math.roundToInt
import kotlin.math.sqrt

/**
 * This function rounds a [Double] to a specified number of decimal places.
 *
 * @param places The number of decimal places to round to.
 * @return A [String] representation of the rounded number.
 */
private fun Double.roundTo(places: Int): String {
  val factor = 10.0.pow(places)
  val rounded = (this * factor).roundToInt() / factor
  return if (rounded % 1.0 == 0.0) rounded.toInt().toString() else rounded.toString()
}

/**
 * This function converts a [Long] representing milliseconds to a formatted timestamp string.
 *
 * @return A [String] representing the time in minutes and seconds.
 */
@Suppress("unused")
fun Long.toTimestamp(): String {
  val minutes = (this / 1000 / 60).toInt()
  val seconds = (this / 1000.0 % 60).roundTo(2)
  return if (minutes > 0) "$minutes minutes and $seconds seconds" else "$seconds seconds"
}

/**
 * This function calculates the square root of a number and rounds it to a specified number of decimal places.
 *
 * @param min The number to calculate the square root of.
 * @param max The number of decimal places to round to.
 * @return A [List] representation of the square root rounded to the specified number of decimal places.
 */
@Suppress("unused")
fun factorPairsBetween(min: Int = 1, max: Int): List<Pair<Int, Int>> {
  return (min..sqrt(max.toDouble()).toInt())
    .filter { max % it == 0 }
    .map { it to max / it }
}

/**
 * This function finds all pairs of integers between a specified range where one number is divisible by the other.
 *
 * @param min The minimum value of the range (inclusive).
 * @param max The maximum value of the range (inclusive).
 * @param allowSameValuesInPairs If true, allows pairs where both numbers are the same (e.g., (4, 4)).
 * @return A [List] of pairs of [Int] where the first number is divisible by the second.
 */
@Suppress("unused")
fun divisiblePairsBetween(min: Int, max: Int, allowSameValuesInPairs: Boolean = false): List<Pair<Int, Int>> {
  return (min..max).flatMap { i ->
    (min..sqrt(i.toDouble()).toInt()).flatMap { j ->
      val pairs = mutableListOf<Pair<Int, Int>>()
      if (i % j == 0) {
        if (allowSameValuesInPairs || i != j) pairs.add(i to j)
        if (j != i / j && (allowSameValuesInPairs || i != i / j)) pairs.add(i to i / j)
      }
      pairs
    }
  }
}

/**
 * This function checks if a random number is lower than a specified chance.
 *
 * @param chance The chance of success (0 to 100).
 * @param outOf The total possible value (default is 100).
 * @return A [Boolean] indicating whether the random number is lower than the chance.
 */
fun chanceOf(chance: Number, outOf: Number = 100): Boolean = ThreadLocalRandom.current().nextDouble(outOf.toDouble()) < chance.toDouble()