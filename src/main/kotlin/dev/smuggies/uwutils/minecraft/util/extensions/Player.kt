package dev.smuggies.uwutils.minecraft.util.extensions

import dev.smuggies.uwutils.minecraft.util.messages.ColorUtil.string
import org.bukkit.entity.Player

/**
 * Sends a colored message to the [Player].
 * @param message The [message] to send.
 */
fun Player.sendMiniMessage(message: String) = sendMessage(string(message))

/**
 * Clears the inventory of the [Player].
 */
fun Player.clearInventory() = inventory.clear()