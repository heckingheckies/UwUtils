package dev.smuggies.uwutils.minecraft.util.extensions

import org.bukkit.inventory.PlayerInventory

/**
 * Clears the player armor.
 */
fun PlayerInventory.clearArmor() { armorContents = arrayOf(null, null, null, null) }

/**
 * Clears player offhand.
 */
fun PlayerInventory.clearOffhand() = setItemInOffHand(null)

/**
 * Clears the complete inventory of the player, including armor and offhand.
 */
fun PlayerInventory.clearAll() {
  clear()
  clearOffhand()
  clearArmor()
}