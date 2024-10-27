package dev.smuggies.uwutils.minecraft.utils.extensions

import org.bukkit.potion.PotionEffect

/**
 * Checks if a [PotionEffect] is harmful.
 * @return True if the effect is harmful, false if it is not.
 */
fun PotionEffect.isHarmful(): Boolean {
  return when (type.category.name) {
    "HARMFUL" -> true
    else -> false
  }
}