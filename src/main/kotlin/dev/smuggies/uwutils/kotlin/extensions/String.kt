package dev.smuggies.uwutils.kotlin.extensions

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
fun String.asSmallText(): String = map { smallTextMap.getOrDefault(it.uppercaseChar(), it) }.joinToString("")