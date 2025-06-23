package dev.smuggies.uwutils.minecraft.utils.extensions

import org.bukkit.inventory.PlayerInventory

/**
 * Clears the armor from the [PlayerInventory].
 */
fun PlayerInventory.clearArmor() { armorContents = arrayOf(null, null, null, null) }

/**
 * Clears the off-hand from the [PlayerInventory].
 */
fun PlayerInventory.clearOffhand() = setItemInOffHand(null)

/**
 * Clears the complete [PlayerInventory], including armor and offhand.
 */
@Suppress("unused")
fun PlayerInventory.clearAll() {
  clear()
  clearOffhand()
  clearArmor()
}