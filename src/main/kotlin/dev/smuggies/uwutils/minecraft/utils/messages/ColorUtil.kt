package dev.smuggies.uwutils.minecraft.utils.messages

import dev.smuggies.uwutils.kotlin.colors.ColorNameBuilder
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

/**
 * A utility class for coloring anything using [MiniMessage].
 *
 * Since the use of [Component]'s is recommended within the Minecraft development sphere,
 * this util is designed to make my life easier.
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
object ColorUtil {

  /**
   * The [serializer] used to colorize [String]'s.
   */
  private val serializer = MiniMessage.builder()
    .tags(TagResolver.standard())
    .build()

  /**
   * The [colorNames] used to transform `hex`, `RGB` or `LAB` colors into names.
   * Uses the [CIELAB color space](https://en.wikipedia.org/wiki/CIELAB_color_space).
   */
  val colorNames = ColorNameBuilder()
    .loadDefaults()
    .build()

  /**
   * Returns a colorized [Component] with all the decorations from [MiniMessage].
   *
   * @param string The [String] using [MiniMessage] formatting you want to colorize.
   * @return The colored version of your input.
   */
  fun string(string: String): Component = serializer.deserialize(string)

  /**
   * Returns a colorized [Component] with all the decorations from [MiniMessage] while removing the automatic italic text for all items.
   *
   * @param string The [String] using [MiniMessage] formatting you want to colorize.
   * @return The colored version of your input.
   */
  fun nonItalicString(string: String): Component = serializer.deserialize("<!i>$string")

  /**
   * Returns a colorized [Component] with all the decorations from [& legacy formatting](https://minecraft.fandom.com/wiki/Formatting_codes).
   *
   * @param string The [String] using [MiniMessage] formatting you want to colorize.
   * @return The colored version of your input.
   */
  fun stringOld(string: String): Component = LegacyComponentSerializer.legacyAmpersand().deserialize("" + string)

  /**
   * Returns a [String] of the [Component] with all the colors intact.
   *
   * @param component The [Component] you want to serialize to [String].
   * @return The colored version of your input.
   */
  fun componentString(component: Component): String = serializer.serialize(component)

  /**
   * Returns a [String] of the [Component] with all the colors stripped.
   *
   * @param component The [Component] you want to serialize to [String].
   * @return The colored version of your input.
   */
  fun stripColor(component: Component): String = PlainTextComponentSerializer.plainText().serialize(component)

  /**
   * Returns a [CharArray] from a given [Component].
   *
   * @param component The [Component] you want to serialize to [CharArray].
   * @return The [CharArray] from your input.
   */
  fun componentToCharArray(component: Component): CharArray = componentString(component).toCharArray()

  /**
   * Returns a [Component] from a given [CharArray].
   *
   * @param chars The [CharArray] you want to serialize to a [Component].
   * @return The [Component] from your input.
   */
  fun charArrayToComponent(chars: CharArray): Component = string(chars.joinToString(""))

  /**
   * Extracts an [Int] from a given [Component].
   *
   * Kind of an unnecessary function for a lot of projects, but why not~
   *
   * @param component The [Component] you want to get the [Int] from.
   * @return The [Int] inside of the [Component], or `null` if there is no [Int].
   */
  fun extractNumberFromComponent(component: Component): Int? {
    val strippedComponent: String = stripColor(component)
    val regexComponent: String = strippedComponent.replace("\\D".toRegex(), "")
    return if (regexComponent.isNotEmpty()) regexComponent.toInt() else null
  }
}