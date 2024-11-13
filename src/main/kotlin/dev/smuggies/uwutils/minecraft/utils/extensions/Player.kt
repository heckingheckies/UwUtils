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
 * Checks if the [Player] has a full `Inventory`.
 * @return True if the `Inventory` is full, false if it is not.
 */
fun Player.hasFullInventory(): Boolean = inventory.firstEmpty() == -1

/**
 * Checks if the [Player] has a full `EnderChest`.
 * @return True if the `EnderChest` is full, false if it is not.
 */
fun Player.hasFullEnderChest(): Boolean = enderChest.firstEmpty() == -1

/**
 * Kills the [Player].
 */
fun Player.kill() = damage(Int.MAX_VALUE.toDouble())

/**
 * Removes all active `PotionEffect`'s from the [Player].
 */
fun Player.removeActivePotionEffects() = activePotionEffects.forEach { removePotionEffect(it.type) }