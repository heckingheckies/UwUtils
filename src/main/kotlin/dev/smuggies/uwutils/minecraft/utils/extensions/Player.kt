package dev.smuggies.uwutils.minecraft.utils.extensions

import org.bukkit.entity.Player

/**
 * Clears the `Inventory` of the [Player].
 */
@Suppress("unused")
fun Player.clearInventory() = inventory.clear()

/**
 * Clears the `EnderChest` of the [Player].
 */
@Suppress("unused")
fun Player.clearEnderChest() = enderChest.clear()

/**
 * Checks if the [Player] has a full `Inventory`.
 * @return True if the `Inventory` is full, false if it is not.
 */
@Suppress("unused")
fun Player.hasFullInventory(): Boolean = inventory.firstEmpty() == -1

/**
 * Checks if the [Player] has a full `EnderChest`.
 * @return True if the `EnderChest` is full, false if it is not.
 */
@Suppress("unused")
fun Player.hasFullEnderChest(): Boolean = enderChest.firstEmpty() == -1

/**
 * Kills the [Player].
 */
@Suppress("unused")
fun Player.kill() = damage(Int.MAX_VALUE.toDouble())

/**
 * Removes all active `PotionEffect`'s from the [Player].
 */
@Suppress("unused")
fun Player.removeActivePotionEffects() = activePotionEffects.forEach { removePotionEffect(it.type) }