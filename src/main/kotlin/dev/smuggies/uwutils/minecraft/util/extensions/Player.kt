package dev.smuggies.uwutils.minecraft.util.extensions

import org.bukkit.entity.Player

/**
 * Clears the inventory of the [Player].
 */
fun Player.clearInventory() = inventory.clear()

/**
 * Kills the [Player].
 */
fun Player.kill() = damage(Int.MAX_VALUE.toDouble())

fun Player.removeActivePotionEffects() = activePotionEffects.forEach { removePotionEffect(it.type) }