package dev.smuggies.uwutils.minecraft.util.messages

import dev.smuggies.uwutils.minecraft.util.messages.ColorUtil.string
import java.awt.Color
import java.awt.image.BufferedImage
import java.net.URI
import javax.imageio.ImageIO

/**
 * This class is used to create a player skull, which can be used anywhere.
 */
@Suppress("unused")
object SkullUtil {

  /**
   * This function gets the player's head image from the minotar site.
   *
   * @param username The username of the player.
   * @return The player's head image.
   */
  private fun headImage(username: String): BufferedImage {
    val url = URI("https://minotar.net/helm/${username}/8.png").toURL()
    return ImageIO.read(url)
  }

  /**
   * This function uses the image and converts it to a chat message.
   *
   * @param image The image just created.
   * @return The chat message from the image.
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
   * This function converts the player's head image to chat lines.
   *
   * @param username The username of the player.
   */
  fun skullLines(username: String) = imageToChat(headImage(username)).split("\n").toMutableList()

  /**
   * This class is used to build a chat message from the player's converted head image.
   *
   * @param username The username of the player.
   */
  class SkullTextBuilder(username: String) {

    private val lines = skullLines(username)

    fun line(line: Int, text: String): SkullTextBuilder {
      lines[line] = "${lines[line]} $text"
      return this
    }

    fun appendLine(line: Int, text: String): SkullTextBuilder {
      lines[line] += text
      return this
    }

    fun prependLine(line: Int, text: String): SkullTextBuilder {
      lines[line] = text + lines[line]
      return this
    }

    fun build() = string(lines.joinToString("\n"))
  }
}