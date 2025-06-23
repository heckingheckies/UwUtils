package dev.smuggies.uwutils.kotlin.extensions

/**
 * Replaces all occurrences of a given text in the [String], ignoring case.
 *
 * @param text The text to be replaced.
 * @param replacement The text to replace with.
 * @return A new [String] with the replacements applied.
 */
@Suppress("unused")
fun String.replaceCaseInsensitive(text: String, replacement: String): String =
  replace(Regex("(?i)$text"), replacement)

/**
 * Converts the [String] into a pretty format by replacing dashes and underscores with spaces
 * and capitalizing the first letter of each word.
 *
 * @return A pretty version of the [String].
 */
@Suppress("unused")
fun String.prettify(): String =
  split(Regex("[-_]")).joinToString(" ") {
    it.lowercase().replaceFirstChar { char -> char.uppercase() }
  }

/**
 * Truncates the [String] to the specified length and appends "..." if it exceeds the length.
 *
 * @param length The maximum length of the string.
 * @return The truncated [String] with "..." appended if necessary.
 */
@Suppress("unused")
fun String.cutOff(length: Int): String =
  if (this.length > length) take(length) + "..." else this

/**
 * Replaces Placeholders in the [String] with corresponding values from the [Map].
 * Placeholders are enclosed in the specified parenthesis characters.
 *
 * @param map A [Map] of placeholder keys to their replacement values.
 * @param parenthesis The enclosing characters for placeholders (default is "{}").
 * @param ignoreCase Whether to ignore case when replacing placeholders.
 * @return A new [String] with placeholders replaced.
 */
@Suppress("unused")
fun String.replacePlaceholders(
  map: Map<String, String>,
  parenthesis: String = "{}",
  ignoreCase: Boolean = false
): String = map.entries.fold(this) { result, (key, value) -> result.replace("${parenthesis[0]}$key${parenthesis[1]}", value, ignoreCase) }

/**
 * Replaces all occurrences of keys in the [Map] with their corresponding values.
 *
 * @param map A [Map] of keys to their replacement values.
 * @param ignoreCase Whether to ignore case when replacing.
 * @return A new [String] with replacements applied.
 */
fun String.replacePlaceholders(map: Map<String, String>, ignoreCase: Boolean = false): String =
  map.entries.fold(this) { result, (key, value) -> result.replace(key, value, ignoreCase) }

/**
 * A [Map] of all characters and their replacements.
 */
val smallTextMap = mapOf(
  'A' to 'ᴀ',
  'B' to 'ʙ',
  'C' to 'ᴄ',
  'D' to 'ᴅ',
  'E' to 'ᴇ',
  'F' to 'ꜰ',
  'G' to 'ɢ',
  'H' to 'ʜ',
  'I' to 'ɪ',
  'J' to 'ᴊ',
  'K' to 'ᴋ',
  'L' to 'ʟ',
  'M' to 'ᴍ',
  'N' to 'ɴ',
  'O' to 'ᴏ',
  'P' to 'ᴘ',
  'Q' to 'ǫ',
  'R' to 'ʀ',
  'S' to 's',
  'T' to 'ᴛ',
  'U' to 'ᴜ',
  'V' to 'ᴠ',
  'W' to 'ᴡ',
  'X' to 'x',
  'Y' to 'ʏ',
  'Z' to 'ᴢ',
  '1' to '₁',
  '2' to '₂',
  '3' to '₃',
  '4' to '₄',
  '5' to '₅',
  '6' to '₆',
  '7' to '₇',
  '8' to '₈',
  '9' to '₉'
)

/**
 * Converts a [String] to small characters seen in a lot of servers where each character is replaced according to the specified character [Map].
 * If a character is not found in the [Map], it remains unchanged.
 *
 * Thanks [Josh](https://github.com/joshbker) :3
 *
 * @return The new [String] with characters replaced according to the character [Map].
 */
@Suppress("unused")
fun String.asSmallText(): String = map { smallTextMap.getOrDefault(it.uppercaseChar(), it) }.joinToString("")