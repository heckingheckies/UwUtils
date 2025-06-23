package dev.smuggies.uwutils.kotlin.misc

import dev.smuggies.uwutils.kotlin.chanceOf
import dev.smuggies.uwutils.kotlin.extensions.replacePlaceholders
import dev.smuggies.uwutils.kotlin.util.Randomizer

/**
 * List of possible endings for UwUified text.
 */
private val endings = mutableListOf(
  "uwu *nuzzles you*", "*kisses you*", "*cuddles you*", "*huggles*", "*blushes*",
  "*hehe*", "*teehee*", "*giggles*",
  "meow", "rawr", "*purr*", "x3",
  "owo", "uwu", ";3", ":3",
  "※(^o^)/※", "※(^o^)/※", "(｡◕‿‿◕｡)", "(｡◕‿‿◕｡)", "(︶︹︶)", "(︶︹︶)", "(・3・)", "(ﾉ◕ヮ◕)ﾉ*:・ﾟ✧",
  "*notices your presence*", "*sweats nervously*", "*blushes furiously*", "*glances away shyly*",
  "*stares kawaii-ly*",
  "owo whats this", "*pounces on you*", "*notices bulge*"
)

/**
 * A [Map] of all possible characters and their replacements.
 */
private val replacements = mapOf(
  "\\. " to "~ ",
  " to " to "~ ",
  "- " to "~ ",
  "\\? " to "~ ",
  "hurt" to "hUWUrt",
  "kill" to "hwuwrt",
  "you" to "you<3",
  "r" to "w",
  "l" to "w",
  "uwu" to "UWU",
  "owo" to "OWO",
  ";-;" to "(-_-)",
  "-_-" to "(-_-)",
  ":o" to "※(^o^)/※",
  ":0" to "※(^o^)/※",
  ":\\)" to "(｡◕‿‿◕｡)",
  ":>" to "(｡◕‿‿◕｡)",
  ":\\(" to "(︶︹︶)",
  ":<" to "(︶︹︶)",
  ":3" to "(・3・)",
  ":D" to "(ﾉ◕ヮ◕)ﾉ*:・ﾟ✧",
  "\\._\\." to "(っ´ω`c)",
  "fuck" to "fwick",
  "shit" to "*poops*",
  "wtf" to "whawt the fwick",
  "wth" to "whawt the hecc"
)

/**
 * A [Randomizer] that selects a random ending from the list of [endings].
 */
private val randomEnding = Randomizer(endings)

/**
 * UwUifies the given text by applying replacements, adding stutters, and appending a random ending.
 *
 * @param text The input text to UwUify.
 * @return The UwUified version of the input text.
 */
@Suppress("unused")
fun uwuify(text: String): String {
  val uwuified = buildString {
    append(text)
    if (chanceOf(65)) append("~")
    append(" ${randomEnding.next()}")
  }.replacePlaceholders(replacements, ignoreCase = true)
  return addStutters(uwuified)
}

/**
 * Adds stutters to the text by randomly stuttering words.
 *
 * @param text The input text to add stutters to.
 * @return The text with stutters added.
 */
private fun addStutters(text: String): String {
  if (!chanceOf(35)) return text
  return text.replace(Regex("\\b([a-zA-Z])([a-zA-Z]*)\\b")) { "${it.groupValues[1]}-${it.groupValues[1]}${it.groupValues[2]}" }
}