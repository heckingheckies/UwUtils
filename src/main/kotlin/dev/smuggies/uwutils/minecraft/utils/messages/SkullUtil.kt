package dev.smuggies.uwutils.minecraft.utils.messages

import dev.smuggies.uwutils.minecraft.utils.messages.ColorUtil.string
import java.awt.Color
import java.awt.image.BufferedImage
import java.net.URI
import javax.imageio.ImageIO

/**
 * Used to create a `Player` skull, which can be used anywhere.
 */
@Suppress("unused")
object SkullUtil {

  /**
   * Fetches the `Player`'s head image from the [Minotar](https://minotar.net/) site.
   *
   * @param username The username of the `Player`.
   * @return The `Player`'s head as a [BufferedImage].
   */
  private fun headImage(username: String): BufferedImage {
    val url = URI("https://minotar.net/helm/${username}/8.png").toURL()
    return ImageIO.read(url)
  }

  /**
   * Uses the [BufferedImage] and converts it to a chat message.
   *
   * @param image The [BufferedImage] just created.
   * @return The chat message from the [BufferedImage].
   */
  private fun imageToChat(image: BufferedImage): String {
    val sb = StringBuilder()
    for (y in 0 until image.height) {
      for (x in 0 until image.width) {
        val color = Color(image.getRGB(x, y))
        val chatColor = String.format("<#%02x%02x%02x>", color.red, color.green, color.blue)
        sb.append(chatColor).append("█")
      }
      sb.append("\n")
    }
    return sb.toString().removeSuffix("\n")
  }

  /**
   * Converts the `Player`'s head image to chat lines.
   *
   * @param username The username of the `Player`.
   */
  fun skullLines(username: String) = imageToChat(headImage(username)).split("\n").toMutableList()

  /**
   * Builds a chat message from the `Player`'s converted head image.
   *
   * @param username The username of the `Player`.
   */
  class SkullTextBuilder(username: String) {

    private val lines = skullLines(username)

    /**
     * Creates a new [line].
     *
     * @param line The row of the [line].
     * @param text The text of the [line].
     */
    fun line(line: Int, text: String): SkullTextBuilder {
      lines[line] = "${lines[line]} $text"
      return this
    }

    /**
     * Appends a [line].
     *
     * @param line The row of the [line].
     * @param text The text of the [line].
     */
    fun appendLine(line: Int, text: String): SkullTextBuilder {
      lines[line] += text
      return this
    }

    /**
     * Prepends a [line].
     *
     * @param line The row of the [line].
     * @param text The text of the [line].
     */
    fun prependLine(line: Int, text: String): SkullTextBuilder {
      lines[line] = text + lines[line]
      return this
    }

    /**
     * Builds the [line]'s and joins them together.
     */
    fun build() = string(lines.joinToString("\n"))
  }
}