package dev.smuggies.uwutils.minecraft.util.messages

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer

/**
 * A utility class for coloring anything using [MiniMessage].
 *
 * Since the use of [Component]'s is recommended within the Minecraft development sphere, this class is designed to make my life easier.
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
   * Returns a colorized [Component] with all the decorations from [MiniMessage].
   *
   * @param string The [String] using [MiniMessage] formatting you want to colorize.
   * @return The colored version of your input.
   */
  fun string(string: String): Component { return serializer.deserialize(string) }

  /**
   * Returns a colorized [Component] with all the decorations from & legacy formatting.
   *
   * @param string The [String] using [MiniMessage] formatting you want to colorize.
   * @return The colored version of your input.
   */
  fun stringOld(string: String): Component { return LegacyComponentSerializer.legacyAmpersand().deserialize("" + string) }

  /**
   * Returns a [String] of the [Component] with all the colors intact.
   *
   * @param component The [Component] you want to serialize to [String].
   * @return The colored version of your input.
   */
  fun componentString(component: Component): String { return serializer.serialize(component) }

  /**
   * Returns a [String] of the [Component] with all the colors stripped.
   *
   * @param component The [Component] you want to serialize to [String].
   * @return The colored version of your input.
   */
  fun stripColor(component: Component): String { return PlainTextComponentSerializer.plainText().serialize(component) }

  /**
   * Returns a [CharArray] from a given [Component].
   *
   * @param component The [Component] you want to serialize to [CharArray].
   * @return The [CharArray] from your input.
   */
  fun componentToCharArray(component: Component): CharArray { return componentString(component).toCharArray() }

  /**
   * Returns a [Component] from a given [CharArray].
   *
   * @param chars The [CharArray] you want to serialize to a [Component].
   * @return The [Component] from your input.
   */
  fun charArrayToComponent(chars: CharArray): Component { return string(chars.joinToString("")) }

  /**
   * Extracts an [Int] from a given [Component].
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