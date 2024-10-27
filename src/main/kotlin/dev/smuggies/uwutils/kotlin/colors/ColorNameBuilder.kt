package dev.smuggies.uwutils.kotlin.colors

import com.github.ajalt.colormath.model.RGB
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

/**
 * Builder for loading and caching [ColorNames], giving you a [ColorNames] instance.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class ColorNameBuilder {
  private val colorNames = mutableListOf<CachedColor>()

  /**
   * Loads the default color names from the `resources`.
   *
   * @return The [ColorNameBuilder] for chaining.
   */
  fun loadDefaults(): ColorNameBuilder {
    val inputStream = javaClass.getResourceAsStream("/colornames.csv") ?: throw IllegalStateException("colornames.csv not found")
    inputStream.reader(StandardCharsets.UTF_8).use { colorNames.addAll(parseColorCSV(it)) }
    return this
  }

  /**
   * Builds the [ColorNames] instance.
   *
   * @return The [ColorNames] instance.
   */
  fun build(): ColorNames = ColorNames(colorNames)

  /**
   * Loads the color names from the given [InputStreamReader].
   *
   * @param reader The [InputStreamReader] to load the color names from.
   *
   * @return The [ColorNameBuilder] for chaining.
   */
  private fun parseColorCSV(reader: InputStreamReader): List<CachedColor> {
    return reader.buffered().useLines { lines ->
      lines.drop(1).map { line ->
        val (name, hex) = line.split(',')
        val lab = RGB(hex).toLAB()
        CachedColor(name, lab.l, lab.a, lab.b)
      }.toList()
    }
  }
}