package dev.smuggies.uwutils.minecraft.utils.extensions

import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable

/**
 * Repairs an [ItemStack] if it [isDamaged].
 * @return True if the item was repaired, false if it was not damaged.
 */
fun ItemStack.repair(): Boolean {
  if (!isDamaged()) return false
  val meta = itemMeta as Damageable
  meta.damage = 0
  itemMeta = meta
  return true
}

/**
 * Checks if an [ItemStack] is damaged.
 * @return True if the item is damaged, false if it is not.
 */
fun ItemStack.isDamaged(): Boolean {
  if (itemMeta !is Damageable) return false
  return ((itemMeta as Damageable).damage != 0)
}