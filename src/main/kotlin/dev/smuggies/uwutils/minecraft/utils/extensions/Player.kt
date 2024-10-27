package dev.smuggies.uwutils.minecraft.utils.extensions

import org.bukkit.entity.Player

/**
 * Clears the `Inventory` of the [Player].
 */
fun Player.clearInventory() = inventory.clear()

/**
 * Clears the `EnderChest` of the [Player].
 */
fun Player.clearEnderChest() = enderChest.clear()

/**
 * Kills the [Player].
 */
fun Player.kill() = damage(Int.MAX_VALUE.toDouble())

/**
 * Removes all active `PotionEffect`'s from the [Player].
 */
fun Player.removeActivePotionEffects() = activePotionEffects.forEach { removePotionEffect(it.type) }