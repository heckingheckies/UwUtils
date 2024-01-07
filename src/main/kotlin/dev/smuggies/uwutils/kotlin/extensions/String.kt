package dev.smuggies.uwutils.kotlin.extensions

val smallTextMap = mapOf(
  'Q' to 'ꞯ',
  'W' to 'ᴡ',
  'E' to 'ᴇ',
  'R' to 'ʀ',
  'T' to 'ᴛ',
  'Y' to 'ʏ',
  'U' to 'ᴜ',
  'I' to 'ɪ',
  'O' to 'ᴏ',
  'P' to 'ᴘ',
  'A' to 'ᴀ',
  'S' to 's',
  'D' to 'ᴅ',
  'F' to 'ꜰ',
  'G' to 'ɢ',
  'H' to 'ʜ',
  'J' to 'ᴊ',
  'K' to 'ᴋ',
  'L' to 'ʟ',
  'Z' to 'ᴢ',
  'X' to 'x',
  'C' to 'ᴄ',
  'V' to 'ᴠ',
  'B' to 'ʙ',
  'N' to 'ɴ',
  'M' to 'ᴍ'
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